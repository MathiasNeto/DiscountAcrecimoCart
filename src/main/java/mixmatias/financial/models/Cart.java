package mixmatias.financial.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Data
@NoArgsConstructor
public class Cart implements Set<Item> {

    HashSet<Item> items = new HashSet<>();


    public void insertItemCart(Item item) {
        items.add(item);
    }


    public void insertAdditionTotal(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("O valor de acréscimo total deve ser maior que zero.");
            return;
        }

        BigDecimal additionItemDynamic = valor.divide(BigDecimal.valueOf(items.size()), 2, RoundingMode.HALF_UP);

        for (Item item : items) {
            item.insertAddition(item.getCodeItem(), additionItemDynamic);
        }
    }

    public void insertDiscountTotal(BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("O valor de desconto total deve ser maior que zero.");
            return;
        }

        BigDecimal discountItemDynamic = valor.divide(BigDecimal.valueOf(items.size()), 2, RoundingMode.HALF_UP);

        for (Item item : items) {
            item.insertDiscount(item.getCodeItem(), discountItemDynamic);
        }
    }

    public void finishPurchase() {
        BigDecimal sumDiscounts = new BigDecimal("0.0");
        BigDecimal sumAdditions = new BigDecimal("0.0");
        BigDecimal sumAllTotal = new BigDecimal("0.0");
        for (Item item : items) {
            sumAdditions = sumAdditions.add(item.getAddition());
            sumDiscounts = sumDiscounts.add(item.getDiscount());
            sumAllTotal = sumAllTotal.add(item.getTotal());
            System.out.println(item.toString());

        }
        System.out.printf("\nsoma de todos os acrescimos {%s} \nsoma de todos os discontos: {%s} \nsoma do total {%s}%n", sumAdditions, sumDiscounts, sumAllTotal);

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Item item) {
        for (Item existing : items) {
            if (existing.getCodeItem().equals(item.getCodeItem())) {
                System.out.println("Item ja existe na base de dados");
                return false;
            }
        }
        return items.add(item);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
