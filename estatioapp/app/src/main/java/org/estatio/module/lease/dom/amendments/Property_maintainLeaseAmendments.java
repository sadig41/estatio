package org.estatio.module.lease.dom.amendments;

import javax.annotation.Nullable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Mixin;

import org.estatio.module.asset.dom.Property;

@Mixin
public class Property_maintainLeaseAmendments {

    private final Property property;

    public Property_maintainLeaseAmendments(Property property) {
        this.property = property;
    }

    @Action()
    public LeaseAmendmentManager $$(@Nullable final LeaseAmendmentTemplate leaseAmendmentTemplate, @Nullable final LeaseAmendmentState leaseAmendmentState) {
        return new LeaseAmendmentManager(property, leaseAmendmentTemplate, leaseAmendmentState);
    }

}
