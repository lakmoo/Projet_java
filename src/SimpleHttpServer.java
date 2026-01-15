import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.ArrayList;
import java.util.List;

public class SimpleHttpServer {
    public static void main(String[] args) {
        try {
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
        } catch (IOException e) {
            // error message if connection not successful
            System.out.println("There's an error starting the server: " + e.getMessage());
        }
    }

    /**
     * something
     */
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

    /**
     * something else
     */
    static class ProgrammeurAPIHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // sample programmers
                List<Programmeur> programmers = Start.creerProgrammeursDeTest();

                // converting to JSON
                String jsonResponse = Programmeur.programmeurToJSON(programmers);

                // set headers
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); // Allow CORS

                // send response
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();

                System.out.println("Sent programmeurs data: " + jsonResponse);
            }
        }
    }
}
