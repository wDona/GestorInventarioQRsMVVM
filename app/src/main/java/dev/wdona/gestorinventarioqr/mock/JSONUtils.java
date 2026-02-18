package dev.wdona.gestorinventarioqr.mock;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JSONUtils {
    public static JSONObject cargarJSONDesdeArchivo(String nombreArchivo) throws JSONException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(nombreArchivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new JSONObject(String.valueOf(sb));
    }

    public static void escribirJSONDeNuevo(JSONObject json, String nombreArchivo) {
        try (java.io.FileWriter file = new java.io.FileWriter(nombreArchivo)) {
            file.write(json.toString(4)); // Indentación de 4 espacios para mejor legibilidad
            System.out.println("Archivo JSON guardado exitosamente: " + nombreArchivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void anadirJSONObjectAlArchivo(JSONObject json, String nombreArchivo) {
        try {
            JSONObject jsonArchivo = cargarJSONDesdeArchivo(nombreArchivo);
            String idProducto = String.valueOf(json.getInt("id"));
            jsonArchivo.put(idProducto, json);
            escribirJSONDeNuevo(jsonArchivo, nombreArchivo);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarJSONObjectEnArchivo(JSONObject json, String nombreArchivo) {
        try {
            JSONObject jsonArchivo = cargarJSONDesdeArchivo(nombreArchivo);
            String idProducto = String.valueOf(json.getInt("id"));
            if (jsonArchivo.has(idProducto)) {
                jsonArchivo.put(idProducto, json);
                escribirJSONDeNuevo(jsonArchivo, nombreArchivo);
            } else {
                System.out.println("Error: No se encontró el producto con ID " + idProducto + " en el archivo.");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
