package pers.sakorua.pojo;

import lombok.Data;
import pers.sakorua.pojo.bin.Bank;
import pers.sakorua.pojo.bin.Country;
import pers.sakorua.pojo.bin.Number;

/**
 * @author SaKoRua
 * @date 2022-01-21 12:29 PM
 * @Description //TODO
 */
@Data
public class Bin {
    private Bank bank;
    private Number number;
    private Country country;
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
}
