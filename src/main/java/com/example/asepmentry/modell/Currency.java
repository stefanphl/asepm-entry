package com.example.asepmentry.modell;

import javax.persistence.*;

@Table
@Entity
public class Currency {

    //Code":"ZAR","Symbol":"R","ThousandsSeparator":",","DecimalSeparator":".","SymbolOnLeft":true,
    // "SpaceBetweenAmountAndSymbol":true,"RoundingCoefficient":0,"DecimalDigits":2}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String symbol;

    @Column
    private String thousandsSeparator;

    @Column
    private String decimalSeparator;

    @Column
    private Boolean symbolOnLeft;

    @Column
    private Boolean spaceBetweenAmountAndSymbol;

    @Column
    private Integer roundingCoefficient;

    @Column
    private Integer decimalDigits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousandsSeparator() {
        return thousandsSeparator;
    }

    public void setThousandsSeparator(String thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public Boolean getSymbolOnLeft() {
        return symbolOnLeft;
    }

    public void setSymbolOnLeft(Boolean symbolOnLeft) {
        this.symbolOnLeft = symbolOnLeft;
    }

    public Boolean getSpaceBetweenAmountAndSymbol() {
        return spaceBetweenAmountAndSymbol;
    }

    public void setSpaceBetweenAmountAndSymbol(Boolean spaceBetweenAmountAndSymbol) {
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
    }

    public Integer getRoundingCoefficient() {
        return roundingCoefficient;
    }

    public void setRoundingCoefficient(Integer roundingCoefficient) {
        this.roundingCoefficient = roundingCoefficient;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }
}
