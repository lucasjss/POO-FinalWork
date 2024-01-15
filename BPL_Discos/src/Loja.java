
import java.util.HashMap;

public class Loja {
    // Lucas André de Jesus que fez '-' tmj sor

    public static final String NOME1 = "BPL Discos";
    public static final String ENDERECO = "Rua dos Bobos";
    public static final int TELEFONE = 54123458;
    public static final int CNPJ = 12334567;
    public static final Arquivos ARQUIVOS = new Arquivos();
    public static final String CAMINHO_ARQUIVO = "dados.txt";

    public static HashMap<String, Disco> pegaDiscos() {
        HashMap<String, Disco> toReturn = new HashMap<>();
        for (String[] discoArr : ARQUIVOS.ler(CAMINHO_ARQUIVO, " - ")) {
            toReturn.put(discoArr[0], new Disco(discoArr[0], discoArr[1], discoArr[2], discoArr[3]));
        }
        return toReturn;
    }
}