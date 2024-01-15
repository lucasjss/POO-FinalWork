import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

// Feito por todos do grupo

public class Sistema {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private final static int SAIR = 0, CADASTRAR = 1, REMOVER = 2, ATUALIZAR = 3, VISUALIZAR = 4, LISTAR = 5;

    public static void main(String[] args) throws IOException {

        File j = criaArquivo("dados.txt");

        int opcao = 0;
        boolean rodando = true;
        HashMap<String, Disco> discos = Loja.pegaDiscos();

        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String cpf_funcionario = "";

        populaFuncionario(funcionarios);

        while (!login(cpf_funcionario, funcionarios)) {
            System.out.println("Insira o CPF do funcionário: ");
            cpf_funcionario = input.readLine();
            if (!login(cpf_funcionario, funcionarios)) {
                System.out.println("FUNCIONÁRIO INEXISTENTE!");
                continue;
            }
            while (rodando) {
                try {
                    menu();
                    System.out.println("Informe a opção: ");
                    opcao = Integer.parseInt(input.readLine());
                } catch (Exception e) {
                    System.out.println("OPÇÃO NÃO ENCONTRADA!");
                    String errorMessage = "Error: " + e.getMessage() + "\nTime: " + new Date().toString();
                    Arquivos.escrever("excecoes.txt", errorMessage, true);
                    continue;
                }

                switch (opcao) {
                    case SAIR:
                        rodando = false;
                        break;
                    case CADASTRAR:
                        cadastrar(discos);
                        break;
                    case REMOVER:
                        remover(discos);
                        break;
                    case ATUALIZAR:
                        atualizar(discos);
                        break;
                    case VISUALIZAR:
                        visualizar(discos);
                        break;
                    case LISTAR:
                        listar(discos);
                        break;
                    default:
                        System.out.println("OPÇÃO INEXISTENTE!!!");
                }
            }
        }
        j.delete();
        j.createNewFile();
        discos.forEach((x, v) -> {
            Arquivos.escrever("dados.txt", v.toString(), true);
        });
    }

    public static boolean login(String cpf_funcionario, ArrayList<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf_funcionario)) {
                return true;
            }
        }
        return false;
    }

    public static void menu() {

        System.out.println("Bem vindo a " + Loja.NOME1);

        System.out.println("======================");
        System.out.println("Escolha uma função:");
        System.out.println("0 - SAIR");
        System.out.println("1 - CADASTRAR");
        System.out.println("2 - REMOVER");
        System.out.println("3 - ATUALIZAR");
        System.out.println("4 - VISUALIZAR");
        System.out.println("5 - LISTAR");
        System.out.println("======================");
    }

    public static void cadastrar(HashMap<String, Disco> discos) throws IOException {
        System.out.println("INFORME O NOME DO DISCO: ");
        String nomeDisco = input.readLine();
        if (discos.keySet().contains(nomeDisco)) {
            System.out.println("DISCO JÁ EXISTENTE!");
        } else {
            System.out.println("INFORME O AUTOR(A): ");
            String autor = input.readLine();
            System.out.println("INFORME A GRAVADORA: ");
            String gravadora = input.readLine();
            System.out.println("INFORME O ANO DE LANÇAMENTO: ");
            String ano = input.readLine();
            Disco d = new Disco(nomeDisco, autor, gravadora, ano);
            discos.put(nomeDisco, d);
            System.out.println("DISCO CADASTRADO COM SUCESSO, PERFECT!");
        }
    }

    public static void remover(HashMap<String, Disco> discos) throws IOException {
        System.out.println("INFORME O NOME DO DISCO: ");
        String nomeDisco = input.readLine();
        if (discos.keySet().contains(nomeDisco)) {
            discos.remove(nomeDisco);
            System.out.println("REMOVIDO!");
        } else {
            System.out.println("DISCO NÃO ENCONTRADO!");
        }
    }

    public static void atualizar(HashMap<String, Disco> discos) throws IOException {
        System.out.println("INFORME O NOME DO DISCO: ");
        String nomeDisco = input.readLine();

        if (discos.keySet().contains(nomeDisco)) {
            System.out.println("Insira a nova gravadora: ");
            String gravadora = input.readLine();
            System.out.println("Insira o novo autor(a): ");
            String autor = input.readLine();
            System.out.println("Insira o novo ano de lançamento: ");
            String ano = input.readLine();
            discos.get(nomeDisco).setGravadora(gravadora);
            discos.get(nomeDisco).setAutor(autor);
            discos.get(nomeDisco).setAno(ano);
        } else {
            System.out.println("DISCO NÃO ENCONTRADO!");
        }
    }

    public static void visualizar(HashMap<String, Disco> discos) throws IOException {
        System.out.println("INFORME O NOME DO DISCO: ");
        String nomeDisco = input.readLine();
        if (discos.keySet().contains(nomeDisco)) {
            System.out.println(discos.get(nomeDisco).toString());
        } else {
            System.out.println("DISCO NÃO ENCONTRADO!");
        }
    }

    public static void listar(HashMap<String, Disco> discos) throws IOException {
        discos.forEach((k, v) -> System.out.println(k.toString()));
    }

    public static File criaArquivo(String file) {
        File j = new File(file);
        if (!j.exists()) {
            try {
                j.createNewFile();
            } catch (IOException e) {
                String errorMessage = "EXCEÇÃO: " + e.getMessage() + "\n TIME: " + new Date().toString();
                Arquivos.escrever("excecoes.txt", errorMessage, true);
            }
        }
        return j;
    }

    public static void populaFuncionario(ArrayList<Funcionario> funcionarios) {
        funcionarios.add(new Funcionario("Rafael Coelho", "000.000.000-00", "Gerente", 100000));
        funcionarios.add(new Funcionario("Lucas Jesus", "046.484.530-00", "Bibliotecario", 8000));
        funcionarios.add(new Funcionario("Bernardo Reolon", "054.555.710-01", "Rockeiro", 7000));
        funcionarios.add(new Funcionario("Paula Galafassi", "022.574.950-55", "Metaleira", 7000));
    }
}