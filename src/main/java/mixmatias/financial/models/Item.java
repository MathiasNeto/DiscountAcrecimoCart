package mixmatias.financial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long codeItem;
    private BigDecimal discount;
    private BigDecimal addition;
    private String description;
    private BigDecimal total;

    public void calculateTotal(){
        total = (total.add(addition).subtract(discount));
    }

    public void insertAddition(Long codeItem ,BigDecimal addition){
        if(this.codeItem.equals(codeItem)){
            this.addition = this.addition.add(addition);
            calculateTotal();
        }else{
            System.out.println("Acrescimo nao inserido, codigo do item nao encontrado na base de dados");
        }
    }
    public void insertDiscount(Long codeItem ,BigDecimal discount){
        if(this.codeItem.equals(codeItem) && discount.compareTo(total) < 0){
            this.discount = this.discount.add(discount);
            calculateTotal();
        } else if (!getCodeItem().equals(codeItem)) {
            System.out.println("Desconto nao inserido, codigo do item nao encontrado na base de dados");
        } else {
            System.out.println("Operacao nao realizada, desconto nao pode ser maior que o total");
        }
    }



}
