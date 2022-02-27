package pers.sakorua.pojo.bin;

import lombok.Data;

/**
 * @author SaKoRua
 * @date 2022-01-21 12:28 PM
 * @Description //TODO
 */
@Data
public class Country {
    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private Integer latitude;
    private Integer longitude;
}