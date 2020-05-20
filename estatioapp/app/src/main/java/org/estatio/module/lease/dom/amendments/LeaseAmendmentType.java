package org.estatio.module.lease.dom.amendments;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

import org.estatio.module.lease.dom.InvoicingFrequency;
import org.estatio.module.lease.dom.LeaseItemType;

import lombok.Getter;

public enum LeaseAmendmentType {

    COVID_FRA_50_PERC(
            new LocalDate(2020,3,16), // min date because of lease selection
            new BigDecimal("50.00"),
            Arrays.asList(
                    LeaseItemType.RENT,
                    LeaseItemType.RENT_DISCOUNT,
                    LeaseItemType.RENT_DISCOUNT_FIXED
            ),
            new LocalDate(2020,3,16),
            new LocalDate(2020,5,10),
            "FR2052",
            Arrays.asList(
                    new Tuple<>(InvoicingFrequency.QUARTERLY_IN_ADVANCE, InvoicingFrequency.MONTHLY_IN_ADVANCE),
                    new Tuple<>(InvoicingFrequency.QUARTERLY_IN_ARREARS, InvoicingFrequency.MONTHLY_IN_ARREARS)
            ),
            Arrays.asList(
                    LeaseItemType.RENT,
                    LeaseItemType.RENT_DISCOUNT,
                    LeaseItemType.RENT_DISCOUNT_FIXED,
                    LeaseItemType.SERVICE_CHARGE,
                    LeaseItemType.MARKETING
            ),
            new LocalDate(2020,7,1),
            new LocalDate(2020,12,31),
            "A"
            ),
    COVID_FRA_100_PERC(
            new LocalDate(2020,4,1),
            new BigDecimal("100.00"),
            Arrays.asList(
                    LeaseItemType.RENT,
                    LeaseItemType.RENT_DISCOUNT,
                    LeaseItemType.RENT_DISCOUNT_FIXED
            ),
            new LocalDate(2020,4,1),
            new LocalDate(2020,6,30),
            "FR2052",
            Arrays.asList(
                    new Tuple<>(InvoicingFrequency.QUARTERLY_IN_ADVANCE, InvoicingFrequency.MONTHLY_IN_ADVANCE),
                    new Tuple<>(InvoicingFrequency.QUARTERLY_IN_ARREARS, InvoicingFrequency.MONTHLY_IN_ARREARS)
            ),
            Arrays.asList(
                    LeaseItemType.RENT,
                    LeaseItemType.RENT_DISCOUNT,
                    LeaseItemType.RENT_DISCOUNT_FIXED,
                    LeaseItemType.SERVICE_CHARGE,
                    LeaseItemType.MARKETING
            ),
            new LocalDate(2020,7,1),
            new LocalDate(2020,12,31),
            "A"
    ),
    DEMO_TYPE(
            new LocalDate(2020,1,1), null, null, null, null, "ITA_DISCOUNT", null, null, null, null, "-DEM")
    ;

    @Getter
    private final LocalDate amendmentStartDate;

    @Getter
    private final BigDecimal discountPercentage;

    @Getter
    private final List<LeaseItemType> discountAppliesTo;

    @Getter
    private final LocalDate discountStartDate;

    @Getter
    private final LocalDate discountEndDate;

    @Getter
    private final String chargeReferenceForDiscountItem;

    @Getter
    private final List<Tuple<InvoicingFrequency, InvoicingFrequency>> frequencyChanges;

    @Getter
    private final List<LeaseItemType> frequencyChangeAppliesTo;

    @Getter
    private final LocalDate frequencyChangeStartDate;

    @Getter
    private final LocalDate frequencyChangeEndDate;

    @Getter
    private String ref_suffix;

    LeaseAmendmentType(
            final LocalDate amendmentStartDate,
            final BigDecimal discountPercentage,
            final List<LeaseItemType> discountAppliesTo,
            final LocalDate discountStartDate,
            final LocalDate discountEndDate,
            final String chargeReferenceForDiscountItem,
            final List<Tuple<InvoicingFrequency, InvoicingFrequency>> frequencyChanges,
            final List<LeaseItemType> frequencyChangeAppliesTo,
            final LocalDate frequencyChangeStartDate,
            final LocalDate frequencyChangeEndDate,
            final String ref_suffix) {
        this.amendmentStartDate = amendmentStartDate;
        this.discountPercentage = discountPercentage;
        this.discountAppliesTo = discountAppliesTo;
        this.discountStartDate = discountStartDate;
        this.discountEndDate = discountEndDate;
        this.chargeReferenceForDiscountItem = chargeReferenceForDiscountItem;
        this.frequencyChanges = frequencyChanges;
        this.frequencyChangeAppliesTo = frequencyChangeAppliesTo;
        this.frequencyChangeStartDate = frequencyChangeStartDate;
        this.frequencyChangeEndDate = frequencyChangeEndDate;
        this.ref_suffix = ref_suffix;
    }

    public static class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}