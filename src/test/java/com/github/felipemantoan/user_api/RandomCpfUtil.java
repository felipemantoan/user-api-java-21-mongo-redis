package com.github.felipemantoan.user_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;

import com.ibm.icu.text.MessageFormat;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class RandomCpfUtil {
    
    private static int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	public static String cpf() {

        Random random = new Random();

		int n = 9;
		int n1 = random.nextInt(n);
		int n2 = random.nextInt(n);
		int n3 = random.nextInt(n);
		int n4 = random.nextInt(n);
		int n5 = random.nextInt(n);
		int n6 = random.nextInt(n);
		int n7 = random.nextInt(n);
		int n8 = random.nextInt(n);
		int n9 = random.nextInt(n);

		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - (mod(d2, 11));

		if (d2 >= 10)
			d2 = 0;

        return "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;
	}
}
