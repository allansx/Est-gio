import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Estagio {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int exer;

        do {
            System.out.println("Selecione o exercício");
            System.out.println("1. Exercicio 1: ");
            System.out.println("2. Exercicio 2: ");
            System.out.println("3. Exercicio 3: ");
            System.out.println("4. Exercicio 4: ");
            System.out.println("5. Exercicio 5: ");
            System.out.println("0. Encerrar programa");
            exer = ler.nextInt();

            switch (exer) {
                case 0: {
                    System.err.println("Programa encerrado");
                    break;
                }
                case 1: {
                    somarNumeros();
                    break;
                }
                case 2: {
                    sequenciaFibonacci(ler);
                    break;
                }
                case 3: {
                    calcularFaturamento();
                    break;
                }
                case 4: {
                    calcularPercentual(ler);
                    break;
                }
                case 5: {
                    inverterString(ler);
                    break;
                }
                default:
                    System.out.println("Número inválido");
            }
            
            // Pausa para ver a saída antes de continuar
            System.out.println("\nPressione Enter para continuar...");
            ler.nextLine(); // Consome a quebra de linha
            ler.nextLine(); // Espera pela entrada do usuário

        } while (exer != 0);

        ler.close(); // Fecha o scanner
    }

    public static void somarNumeros() {
        int indice = 13;
        int soma = 0;
        int k = 0;

        while (k < indice) {
            k = k + 1;
            soma = soma + k;
        }
        System.out.println("Soma: " + soma); // Resultado será 91
    }

    public static void sequenciaFibonacci(Scanner ler) {
        System.out.println("Informe o número: ");
        int num = ler.nextInt();

        boolean pertence = pertenceFibonacci(num);

        if (pertence) {
            System.out.println(num + " pertence à sequência de Fibonacci");
        } else {
            System.out.println(num + " não pertence à sequência de Fibonacci");
        }
    }

    static boolean pertenceFibonacci(int n) {
        int a = 0, b = 1, proximo = 0;
        if (n == 0 || n == 1) {
            return true;
        }
        while (proximo < n) {
            proximo = a + b;
            a = b;
            b = proximo;
        }
        return proximo == n;
    }

    public static void calcularFaturamento() {
        try {
            // Lê o arquivo JSON
            String content = new String(Files.readAllBytes(Paths.get("resources/faturamento.json")));
            JSONArray faturamento = new JSONArray(content); // Leitura direta como JSONArray

            double menor = Double.MAX_VALUE;
            double maior = Double.MIN_VALUE;
            double soma = 0.0;
            int diasComFaturamento = 0;

            for (int i = 0; i < faturamento.length(); i++) {
                JSONObject dia = faturamento.getJSONObject(i);
                double valor = dia.getDouble("valor");

                if (valor > 0) {
                    if (valor < menor) {
                        menor = valor;
                    }
                    if (valor > maior) {
                        maior = valor;
                    }
                    soma += valor;
                    diasComFaturamento++;
                }
            }

            double media = soma / diasComFaturamento;

            System.out.printf("Menor faturamento: %.2f\n", menor);
            System.out.printf("Maior faturamento: %.2f\n", maior);
            System.out.printf("Média de faturamento: %.2f\n", media);

            // Contando dias com faturamento superior à média
            int diasAcimaMedia = 0;
            for (int i = 0; i < faturamento.length(); i++) {
                JSONObject dia = faturamento.getJSONObject(i);
                if (dia.getDouble("valor") > media) {
                    diasAcimaMedia++;
                }
            }

            System.out.println("Número de dias com faturamento acima da média: " + diasAcimaMedia);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void calcularPercentual(Scanner ler) {
        double sp = 67836.43;
        double rj = 36678.66;
        double mg = 29229.88;
        double es = 27165.48;
        double outros = 19849.53;

        double total = sp + rj + mg + es + outros;

        System.out.printf("Percentual SP: %.2f%%\n", (sp / total) * 100);
        System.out.printf("Percentual RJ: %.2f%%\n", (rj / total) * 100);
        System.out.printf("Percentual MG: %.2f%%\n", (mg / total) * 100);
        System.out.printf("Percentual ES: %.2f%%\n", (es / total) * 100);
        System.out.printf("Percentual Outros: %.2f%%\n", (outros / total) * 100);
    }

    public static void inverterString(Scanner ler) {
        System.out.println("Informe a string: ");
        ler.nextLine(); // Consumir a quebra de linha
        String str = ler.nextLine();
        String inversa = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            inversa += str.charAt(i);
        }

        System.out.println("String invertida: " + inversa);
    }
}
