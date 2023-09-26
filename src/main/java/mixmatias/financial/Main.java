package mixmatias.financial;

import lombok.extern.java.Log;
import mixmatias.financial.models.Cart;
import mixmatias.financial.models.Item;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Cart shoppingCart = new Cart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir item ao carrinho");
            System.out.println("2. Acréscimo de item");
            System.out.println("3. Desconto de item");
            System.out.println("4. Acréscimo total");
            System.out.println("5. Desconto total");
            System.out.println("6. Finalizar venda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código do produto: ");
                    Long code = scanner.nextLong();
                    System.out.print("Descrição: ");
                    String description = scanner.next();
                    BigDecimal addition = new BigDecimal("0.0");
                    BigDecimal discount = new BigDecimal("0.0");
                    BigDecimal total = new BigDecimal("10.00");
                    Item newItem = new Item(code, discount, addition, description, total);
                    shoppingCart.insertItemCart(newItem);
                    break;
                case 2:
                    System.out.print("Código do produto para acréscimo: ");
                    Long codeItemAddition = scanner.nextLong();
                    System.out.print("Valor do acréscimo: ");
                    BigDecimal valueAddition = scanner.nextBigDecimal();
                    for (Item item : shoppingCart.getItems()) {
                        item.insertAddition(codeItemAddition, valueAddition);
                    }
                    break;
                case 3:
                    System.out.print("Código do produto para desconto: ");
                    Long codeItemDiscount = scanner.nextLong();
                    System.out.print("Valor do desconto: ");
                    BigDecimal valueDiscount = scanner.nextBigDecimal();

                    for (Item item : shoppingCart.getItems()) {

                        item.insertDiscount(codeItemDiscount, valueDiscount);
                        System.out.println("Desconto aplicado ao item.");
                        break;
                    }
                    break;
                case 4:
                    System.out.print("Valor do acréscimo total: ");
                    BigDecimal valueAdditionTotal = scanner.nextBigDecimal();
                    shoppingCart.insertAdditionTotal(valueAdditionTotal);
                    break;
                case 5:
                    System.out.print("Valor do desconto total: ");
                    BigDecimal valueDiscountTotal = scanner.nextBigDecimal();
                    shoppingCart.insertDiscountTotal(valueDiscountTotal);
                    break;
                case 6:
                    shoppingCart.finishPurchase();
                    System.exit(0);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}