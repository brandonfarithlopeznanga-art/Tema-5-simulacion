package taller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

public class PythonSimulator {

    public static String ejecutarSimulacion(
            String datosJson) {

        try {

            String comandoPython = "python";

            String archivoPython =
                    "python/simulacion.py";

            ProcessBuilder proceso =
                    new ProcessBuilder(
                            comandoPython,
                            archivoPython
                    );

            proceso.redirectErrorStream(true);

            Process process =
                    proceso.start();

            // Enviar JSON a Python
            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    process.getOutputStream(),
                                    StandardCharsets.UTF_8
                            )
                    );

            writer.write(datosJson);
            writer.flush();
            writer.close();

            // Leer respuesta de Python
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream(),
                                    StandardCharsets.UTF_8
                            )
                    );

            StringBuilder resultado =
                    new StringBuilder();

            String linea;

            while (
                    (linea = reader.readLine())
                            != null
            ) {

                resultado.append(linea);
            }

            int codigoSalida =
                    process.waitFor();

            if (codigoSalida != 0) {

                return "ERROR: Python no pudo ejecutar la simulación.";
            }

            return resultado.toString();

        } catch (Exception e) {

            return "ERROR: " + e.getMessage();
        }
    }
}