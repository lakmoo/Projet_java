package src;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SimpleHttpServer {
    public static void main(String[] args) {
        try {
            // lien base de données
            String databaseURL = "jdbc:sqlite:programmeurs.db";

            // create an HttpServer instance
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

            // create a context for a specific path and set the handler
            server.createContext("/", new MyHandler());

            // API endpoint for employee data
            server.createContext("/api/employees", new ProgrammeurAPIHandler());

            // start the server
            server.setExecutor(null);
            server.start();

            System.out.println("the server is running on port 8000");
            System.out.println("Database: " + databaseURL);
        } catch (IOException e) {
            // error message if connection not successful
            System.out.println("There's an error starting the server: " + e.getMessage());
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // retrieve the path requested
            String requestPath = exchange.getRequestURI().getPath();

            // initialising the corresponding file path
            String filePath;
            if (requestPath.equals("/")) {
                filePath = "src/public/index.html"; // path of the main page
            } else {
                // path of the other pages
                filePath = "src/public" + requestPath;
            }

            Path path = Paths.get(filePath);

            // checks if the file exists, then displays the file content
            if (Files.exists(path)) {
                byte[] fileContent = Files.readAllBytes(path);
                String contentType = getContentType(filePath);

                // set the content type header
                exchange.getResponseHeaders().set("Content-Type", contentType);

                // send response to the server
                exchange.sendResponseHeaders(200, fileContent.length);
                OutputStream os = exchange.getResponseBody();
                os.write(fileContent);
                os.close();

                System.out.println("served the file : " + filePath);
            } else {
                // displays an error if file not found
                String response = ":( Error 404 - File Not Found: " + requestPath;
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                System.out.println("File not found: " + filePath);
            }
        }

        // returns the type of the file
        private String getContentType(String filePath) {
            if (filePath.endsWith(".html")) {
                return "text/html";
            } else if (filePath.endsWith(".css")) {
                return "text/css";
            } else if (filePath.endsWith(".js")) {
                return "application/javascript";
            } else if (filePath.endsWith(".json")) {
                return "application/json";
            } else if (filePath.endsWith(".png")) {
                return "image/png";
            } else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
                return "image/jpeg";
            } else {
                return "text/plain";
            }
        }
    }

    static class ProgrammeurAPIHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Get employees from database
                ActionsBDDImpl test = new ActionsBDDImpl();
                List<Programmeur> listeProgrammeurs = test.getProgrammeurs();

                // Save to JSON file
                Programmeur.creerFichierJSON(listeProgrammeurs, "programmeurs.json");

                // Read the JSON file
                byte[] jsonBytes = Files.readAllBytes(Paths.get("programmeurs.json"));

                System.out.println("Sending employee data from JSON file");

                // Set headers
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");

                // Send response
                exchange.sendResponseHeaders(200, jsonBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonBytes);
                os.close();
            } else if ("DELETE".equals(exchange.getRequestMethod())) {

                String path = exchange.getRequestURI().getPath();
                String[] pathParts = path.split("/");

                if (pathParts.length >= 4) {
                    try {
                        int programmeurId = Integer.parseInt(pathParts[pathParts.length - 1]);

                        System.out.println("Attempting to delete employee with ID: " + programmeurId);

                        // Delete from database
                        ActionsBDDImpl test = new ActionsBDDImpl();
                        boolean success = test.deleteProgrammeur(programmeurId);

                        // Prepare response
                        String jsonResponse;
                        int statusCode;

                        if (success) {
                            jsonResponse = "{\"success\":true,\"message\":\"Employee deleted successfully\"}";
                            statusCode = 200;
                        } else {
                            jsonResponse = "{\"success\":false,\"message\":\"Employee not found\"}";
                            statusCode = 404;
                        }

                        // Send response
                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);

                        OutputStream os = exchange.getResponseBody();
                        os.write(jsonResponse.getBytes());
                        os.close();

                    } catch (NumberFormatException e) {
                        // Invalid ID format
                        String errorResponse = "{\"success\":false,\"message\":\"Invalid employee ID\"}";
                        exchange.sendResponseHeaders(400, errorResponse.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(errorResponse.getBytes());
                        os.close();
                    }
                } else {
                    // Missing ID in URL
                    String errorResponse = "{\"success\":false,\"message\":\"Employee ID required\"}";
                    exchange.sendResponseHeaders(400, errorResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(errorResponse.getBytes());
                    os.close();
                }
            }
        }
    }
}
