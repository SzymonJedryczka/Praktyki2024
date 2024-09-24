//Tutaj wywyłuje metody z pliku Connect.java 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Server_copy {

    private static Logger LOG = Logger.getLogger(Server.class.getName());
    // To jest biblioteka Jackson; musisz to dodac do classpatha
    //https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    private static ObjectMapper objectMapper = new ObjectMapper();

    private record HttpNestedObject(String name, String surname) { }

    private record HttpResponse(String code, String title, String description, String rate) {
    }

    public static void main(String[] args) throws IOException {
        Game lol = new Game("LOL", "League of Legends", "Opis", "4");
        LOG.info("app started");
        int port = 8087;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // To jest endpoint wystawiony przez apke; po wejsciu na http://localhost:8087/api/test pojawi sie odpowiedz
        server.createContext("/api/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (exchange.getRequestMethod().equals("GET")) {
                    try {
                        // Stream do ktorego zapisujemy odpowiedz

                        // pętli while używam do wywołania wszystkich danych z bazy
                        Integer i = 1;
                        while(i<=Connect.table_length()){
                        OutputStream responseBody = exchange.getResponseBody();

                        // To jest potrzebne do oznaczenia odpowiedzi jako JSON wykorzystywany przy REST API
                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        // To przukladowy obiekt ktory jest zwracany w odpowiedzi
                        HttpResponse responseEntity = new HttpResponse(
                                Connect.getCode(i),
                                Connect.getTitle(i),
                                Connect.getDescription(i),
                                Connect.getRate(i)
                            
                        );
                        
                        LOG.info("to jest i: " + i.toString());
                        i++;
                        // Tutaj nastepuje zamiana obiektu na JSONa
                        String jsonResponse = objectMapper.writeValueAsString(responseEntity);
                        LOG.info("Response: " + jsonResponse);

                        // Tutaj wysylamy odpowiedz + naglowki
                        exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                        responseBody.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
                        responseBody.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOG.severe("Unknown error: " + e.getMessage());
                    }
                } else {
                    LOG.warning("Unsupported http method: " + exchange.getRequestMethod());
                    OutputStream responseBody = exchange.getResponseBody();

                    String response = "{\"error\": \"%s\"}".formatted("Unsupported http method");

                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    responseBody.write(response.getBytes(StandardCharsets.UTF_8));
                    responseBody.close();
                }
            }
        });

        // server.createContext("/api/tost", new HttpHandler() {
        //     @Override
        //     public void handle(HttpExchange exchange) throws IOException {
        //         if (exchange.getRequestMethod().equals("GET")) {
        //             try {
        //                 lol.setCode("CS");
        //                 lol.setTitle("Counter-strike");
        //                 // Stream do ktorego zapisujemy odpowiedz
        //                 OutputStream responseBody = exchange.getResponseBody();

        //                 // To jest potrzebne do oznaczenia odpowiedzi jako JSON wykorzystywany przy REST API
        //                 exchange.getResponseHeaders().set("Content-Type", "application/json");

        //                 // To przukladowy obiekt ktory jest zwracany w odpowiedzi
        //                 HttpResponse responseEntity = new HttpResponse(
        //                         "Zmieniono",
        //                         "CS"
        //                 );

        //                 // Tutaj nastepuje zamiana obiektu na JSONa
        //                 String jsonResponse = objectMapper.writeValueAsString(responseEntity);
        //                 LOG.info("Response: " + jsonResponse);

        //                 // Tutaj wysylamy odpowiedz + naglowki
        //                 exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
        //                 responseBody.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        //                 responseBody.close();
        //             } catch (Exception e) {
        //                 e.printStackTrace();
        //                 LOG.severe("Unknown error: " + e.getMessage());
        //             }
        //         } else {
        //             LOG.warning("Unsupported http method: " + exchange.getRequestMethod());
        //             OutputStream responseBody = exchange.getResponseBody();

        //             String response = "{\"error\": \"%s\"}".formatted("Unsupported http method");

        //             exchange.getResponseHeaders().set("Content-Type", "application/json");
        //             exchange.sendResponseHeaders(200, response.getBytes().length);
        //             responseBody.write(response.getBytes(StandardCharsets.UTF_8));
        //             responseBody.close();
        //         }
        //     }
        // });

        server.setExecutor(null);
        server.start();
    }

}
