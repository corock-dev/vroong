package com.coright.vroong;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PriceUnit {

    private final Locale locale;

    public PriceUnit(Locale locale) {
        if (Objects.isNull(locale)) {
            throw new IllegalArgumentException("locale arg is null");
        }

        this.locale = locale;
    }

    /**
     * {@link BigDecimal} 타입의 인자를 받아 적합한 화폐 포맷으로 변경한다.
     * {@link PriceUnit} 클래스 속성인 locale 변수를 사용하여 적합한 화폐 포맷으로 변경한다.
     * <p>
     * {@link NumberFormat} 숫자 문자열을 파싱하거나 숫자를 특정 형태로 포매팅하는 기능을 제공한다.
     * 하지만 이 클래스는 스레드 안전(thread-safe)하지 않아 멀티 스레드 환경에서 정확하게 동작하지 않는다.
     * 그러므로 format 메서드 내부에서 매번 객체를 생성해야 한다.
     *
     * @param price
     * @return
     */
    public String format(BigDecimal price) {
        //
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(Optional.ofNullable(price).orElse(BigDecimal.ZERO));
    }

    /**
     * {@link PriceUnit} 클래스 속성인 locale 변수가 null 이면 {@link PriceUnit#format(BigDecimal)} 메서드는 동작하지 않는다.
     * 그래서 {@link PriceUnit#validate()} 메서드는 locale 변수의 null 검사를 하고, null 인 경우 예외를 생성하여 던진다.
     * {@link PriceUnit} 클래스는 클래스 변수 locale 이 null 이 될 수 없는 클래스 불변식(class invariant)을 갖고 있다.
     * <p>
     * cf. Editor > Inspections > Javadoc > Javadoc declaration problems > Ignore Javadoc pointing to itself
     */
    public void validate() {
        if (Objects.isNull(locale)) {
            throw new IllegalStateException("locale is null");
        }

        log.info("locale is [{}]", locale);
    }

}
