/*
 * RomRaider Open-Source Tuning, Logging and Reflashing
 * Copyright (C) 2006-2013 RomRaider.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.romraider.logger.ecu.ui.swing.menubar.action;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;

import com.romraider.logger.ecu.EcuLogger;
import com.romraider.swing.menubar.action.AbstractAction;

public final class LearningTableValuesAction extends AbstractAction {
    public LearningTableValuesAction(EcuLogger logger) {
        super(logger);
    }

    public final void actionPerformed(ActionEvent actionEvent) {
        if (!logger.isEcuInit()) {
            final String notInit = "To read Learning Table Values the " +
                    "Logger must first intialize with the\n" + 
                    logger.getTarget() +
                    ". Press the Restart button to connect with the Logger then try\n" +
                    "reading the Learning Table Values again.\n";
            showMessageDialog(logger,
                    notInit,
                    "Not Initialized", ERROR_MESSAGE);
        }
        else {
            if (!logger.isLogging() ||
                    (logger.isLogging() &&
                     showConfirmation() == OK_OPTION)) {

                logger.readLearningTables();
            }
        }
    }

    private final int showConfirmation() {
        return showConfirmDialog(logger,
                "Interrupt Logging to read the Learning Table values?",
                "Learning Table Values",
                YES_NO_OPTION,
                QUESTION_MESSAGE);
    }
}
