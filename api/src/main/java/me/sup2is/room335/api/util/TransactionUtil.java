package me.sup2is.room335.api.util;

import lombok.NoArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@NoArgsConstructor
public class TransactionUtil {

    public static void runAfterCommitTransaction(final Runnable r) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

                @Override
                public void afterCommit() {
                    try {
                        r.run();
                    } catch (final Exception e) {
                        throw e;
                    }
                }
            });
        } else {
            try {
                r.run();
            } catch (final Exception e) {
                throw e;
            }
        }
    }
}