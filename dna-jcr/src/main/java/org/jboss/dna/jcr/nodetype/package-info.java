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
/**
 * The DNA specification for programmatically creating JCR {@link javax.jcr.nodetype.NodeDefinition}s.
 * To use, simply obtain the JCR {@link javax.jcr.nodetype.NodeTypeManager} from the {@link javax.jcr.Workspace#getNodeTypeManager() workspace}
 * and cast to a {@link org.jboss.dna.jcr.JcrNodeTypeManager}.  That object can then be used to create new
 * {@link org.jboss.dna.jcr.JcrNodeTypeManager#createNodeDefinitionTemplate() node definition templates},
 * {@link org.jboss.dna.jcr.JcrNodeTypeManager#createNodeTypeTemplate() node type templates},
 * and {@link org.jboss.dna.jcr.JcrNodeTypeManager#createPropertyDefinitionTemplate() property definition templates},
 * and to then {@link org.jboss.dna.jcr.JcrNodeTypeManager#registerNodeType(NodeTypeDefinition, boolean) register} the new node types.
 * <p>
 * This design is patterned after the similar funcationality in the JCR 2.0 Public Final Draft (PFD), and will
 * eventually be migrated to implement the specification when JBoss DNA supports the final JCR 2.0 final specification.
 * </p>
 */

package org.jboss.dna.jcr.nodetype;

