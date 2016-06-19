package com.br.free.commerce.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by pc on 19/06/2016.
 */
public class NumberUtil {

    public static BigDecimal formatToMoney(Double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.isParseBigDecimal();

        return new BigDecimal(df.format(number).replace(",",".")); // dj_segfault

    }
}
