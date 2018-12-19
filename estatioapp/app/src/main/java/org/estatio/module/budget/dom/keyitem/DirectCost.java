/*
 *
 *  Copyright 2012-2015 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.budget.dom.keyitem;

import java.math.BigDecimal;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;

import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;

import org.incode.module.base.dom.utils.TitleBuilder;

import org.estatio.module.asset.dom.Unit;
import org.estatio.module.budget.dom.keytable.DirectCostTable;

import lombok.Getter;
import lombok.Setter;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE
        ,schema = "dbo" // Isis' ObjectSpecId inferred from @DomainObject#objectType
)
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@javax.jdo.annotations.Discriminator("org.estatio.dom.budgeting.keyitem.DirectCost")
@DomainObject(
        editing = Editing.DISABLED,
        objectType = "org.estatio.dom.budgeting.keyitem.DirectCost"
)
public class DirectCost extends PartitioningTableItem {

    public DirectCost(){
        super("partitioningTable, unit, budgetedValue, auditedValue");
    }

    public DirectCost(final DirectCostTable table, final Unit unit, final BigDecimal budgetedValue, final BigDecimal auditedValue){
        this();
        this.setPartitioningTable(table);
        this.setUnit(unit);
        this.budgetedValue = budgetedValue;
        this.auditedValue = auditedValue;
    }

    public String title() {
        return TitleBuilder
                .start()
                .withParent(getPartitioningTable())
                .withName(getUnit())
                .toString();
    }


    @Column(allowsNull = "false", scale = 2)
    @Getter @Setter
    private BigDecimal budgetedValue;

    @ActionLayout(hidden = Where.EVERYWHERE)
    public DirectCost changeBudgetedValue(final BigDecimal budgetedValue) {
        setAuditedValue(budgetedValue);
        return this;
    }

    public BigDecimal default0ChangeBudgetedValue(final BigDecimal budgetedValue) {
        if (getBudgetedValue()!=null) {
            return getBudgetedValue();
        } else {
            return BigDecimal.ZERO;
        }
    }
    public String validateChangeBudgetedValue(final BigDecimal budgetedValue) {
        if (budgetedValue.compareTo(BigDecimal.ZERO) < 0) {
            return "Value cannot be less than zero";
        }
        return null;
    }

    //endregion
    //region > auditedValue (property)

    @Column(allowsNull = "true", scale = 2)
    @Getter @Setter
    private BigDecimal auditedValue;

    @ActionLayout(hidden = Where.EVERYWHERE)
    public DirectCost changeAuditedValue(final BigDecimal auditedKeyValue) {
        setAuditedValue(auditedKeyValue);
        return this;
    }

    public BigDecimal default0ChangeAuditedValue(final BigDecimal auditedKeyValue) {
        if (getAuditedValue()!=null) {
        return getAuditedValue();
        } else {
            return BigDecimal.ZERO;
        }
    }
    public String validateChangeAuditedValue(final BigDecimal auditedKeyValue) {
        if (auditedKeyValue.compareTo(BigDecimal.ZERO) < 0) {
            return "Value cannot be less than zero";
        }
        return null;
    }

    //endregion

    @Action(restrictTo = RestrictTo.PROTOTYPING, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        removeIfNotAlready(this);
    }

    @Override
    @PropertyLayout(hidden = Where.EVERYWHERE)
    public ApplicationTenancy getApplicationTenancy() {
        return getPartitioningTable().getApplicationTenancy();
    }
}