package basicserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {
public static void main(String[] args) throws Exception {
        // Crea un nuevo socket de servidor en el puerto 5000
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Esperando conexiones en el puerto 5000...");

        // Acepta una conexión entrante y crea un nuevo socket de cliente
        Socket clientSocket = serverSocket.accept();
        System.out.println("Conexión aceptada desde " + clientSocket.getInetAddress().getHostAddress());

        // Obtiene los flujos de entrada y salida del socket de cliente
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Lee la primera línea de la solicitud del cliente, que debería ser la línea de solicitud (por ejemplo, "GET /path HTTP/1.1")
        String request = in.readLine();

        // Divide la línea de solicitud en tres partes: el verbo de solicitud (por ejemplo, "GET"), la ruta (por ejemplo, "/path") y el protocolo (por ejemplo, "HTTP/1.1")
        String[] tokens = request.split(" ");
        String method = tokens[0];
        String path = tokens[1];
        String protocol = tokens[2];

        // Si el verbo de solicitud es "GET" y la ruta es "/", escribe una respuesta al cliente
        if (method.equals("GET") && path.equals("/")) {
            out.println(protocol + " 200 OK");
            out.println("Content-Type: text/html;utf-8");
            out.println();
            out.println("<h1>Bienvenido al servidor</h1>");
            out.println("<p>Esta es la pagina principal del servidor.</p>");
        } else {
            // Si el verbo de solicitud o la ruta no son válidos, escribe una respuesta de error al cliente
            out.println(protocol + " 404 Not Found");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<h1>Error 404: Recurso no encontrado</h1>");
        }}}

        //