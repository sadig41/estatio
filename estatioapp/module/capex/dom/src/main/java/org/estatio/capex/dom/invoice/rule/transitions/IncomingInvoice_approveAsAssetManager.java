package org.estatio.capex.dom.invoice.rule.transitions;

import org.apache.isis.applib.annotation.Mixin;

import org.estatio.capex.dom.invoice.IncomingInvoice;
import org.estatio.capex.dom.invoice.rule.IncomingInvoiceTransition;

@Mixin
public class IncomingInvoice_approveAsAssetManager extends IncomingInvoice_transitionAbstract {

    public IncomingInvoice_approveAsAssetManager(IncomingInvoice incomingInvoice) {
        super(incomingInvoice, IncomingInvoiceTransition.APPROVE_AS_ASSET_MANAGER);
    }

}