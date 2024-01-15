import java.io.*;
import java.util.ArrayList;
import java.util.Date;

//Feito por Lucas André de Jesus

public class Arquivos {

    public ArrayList<String[]> ler(String nomeArquivo, String separador) {
        File arquivo = new File(nomeArquivo);
        System.out.println(arquivo.getAbsolutePath());
        ArrayList<String[]> arr = new ArrayList<>();
        try {
            try (FileReader fr = new FileReader(arquivo);
                    BufferedReader bf = new BufferedReader(fr)) {
                String linha_lida;
                do {
                    linha_lida = bf.readLine();
                    if (linha_lida != null && !linha_lida.isEmpty()) {
                        arr.add(linha_lida.split(separador));
                    }
                } while (linha_lida != null);
            }
        } catch (IOException e) {
            System.out.println("ERRO AO LER NO ARQUIVO");
            String errorMessage = "EXCEÇÃO: " + e.getMessage() + "\n TIME: " + new Date().toString();
            Arquivos.escrever("excecoes.txt", errorMessage, true);
        }
        return arr;
    }

    public static void escrever(String nomeArquivo, String pEscrever, boolean append) {
        File arquivo = new File(nomeArquivo);
        try {
            try (FileWriter fw = new FileWriter(arquivo, append);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw)) {
                pw.println(pEscrever);
            }
        } catch (IOException e) {
            System.out.println("ERRO AO ESCREVER NO ARQUIVO");
            String errorMessage = "EXCEÇÃO: " + e.getMessage() + "\n TIME: " + new Date().toString();
            Arquivos.escrever("excecoes.txt", errorMessage, true);
        }
    }
}