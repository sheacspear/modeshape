/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
* See the AUTHORS.txt file in the distribution for a full listing of 
* individual contributors.
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.connector.jbosscache;

import java.util.Locale;
import java.util.Set;
import org.jboss.dna.common.i18n.I18n;

/**
 * The internationalized string constants for the <code>org.jboss.dna.connector.jbosscache*</code> packages.
 */
public final class JBossCacheConnectorI18n {

    public static I18n connectorName;
    public static I18n nodeDoesNotExist;
    public static I18n propertyIsRequired;
    public static I18n locationsMustHavePath;
    public static I18n errorSerializingCachePolicyInSource;
    public static I18n objectFoundInJndiWasNotCache;
    public static I18n objectFoundInJndiWasNotCacheFactory;
    public static I18n unableToDeleteBranch;
    public static I18n unableToCloneWorkspaces;
    public static I18n unableToCreateWorkspaces;
    public static I18n unableToCreateWorkspace;
    public static I18n workspaceAlreadyExists;
    public static I18n workspaceDoesNotExist;
    public static I18n workspaceNameWasNotValidConfiguration;
    public static I18n defaultCacheFactoryConfigurationNameWasNotValidConfiguration;

    static {
        try {
            I18n.initialize(JBossCacheConnectorI18n.class);
        } catch (final Exception err) {
            System.err.println(err);
        }
    }

    public static Set<Locale> getLocalizationProblemLocales() {
        return I18n.getLocalizationProblemLocales(JBossCacheConnectorI18n.class);
    }

    public static Set<String> getLocalizationProblems() {
        return I18n.getLocalizationProblems(JBossCacheConnectorI18n.class);
    }

    public static Set<String> getLocalizationProblems( Locale locale ) {
        return I18n.getLocalizationProblems(JBossCacheConnectorI18n.class, locale);
    }
}
