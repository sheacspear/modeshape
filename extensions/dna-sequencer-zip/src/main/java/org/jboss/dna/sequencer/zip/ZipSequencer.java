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

package org.jboss.dna.sequencer.zip;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.jboss.dna.graph.property.BinaryFactory;
import org.jboss.dna.graph.property.DateTimeFactory;
import org.jboss.dna.graph.sequencer.SequencerOutput;
import org.jboss.dna.graph.sequencer.StreamSequencer;
import org.jboss.dna.graph.sequencer.StreamSequencerContext;

/**
 * A sequencer that processes and extract metadata from ZIP files.
 */
public class ZipSequencer implements StreamSequencer {

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.graph.sequencer.StreamSequencer#sequence(java.io.InputStream,
     *      org.jboss.dna.graph.sequencer.SequencerOutput, org.jboss.dna.graph.sequencer.StreamSequencerContext)
     */
    public void sequence( InputStream stream,
                          SequencerOutput output,
                          StreamSequencerContext context ) {
        BinaryFactory binaryFactory = context.getValueFactories().getBinaryFactory();
        DateTimeFactory dateFactory = context.getValueFactories().getDateFactory();

        try {
            ZipInputStream in = new ZipInputStream(stream);
            ZipEntry entry = in.getNextEntry();
            byte[] buf = new byte[1024];

            // Create top-level node
            output.setProperty("zip:content", "jcr:primaryType", "zip:content");
            while (entry != null) {

                if (entry.isDirectory()) { // If entry is directory, create nt:folder node
                    output.setProperty("zip:content/" + entry.getName(), "jcr:primaryType", "nt:folder");
                } else { // If entry is File, create nt:file
                    output.setProperty("zip:content/" + entry.getName(), "jcr:primaryType", "nt:file");
                    output.setProperty("zip:content/" + entry.getName() + "/jcr:content", "jcr:primaryType", "dna:resource");
                    int n;
                    ByteArrayOutputStream baout = new ByteArrayOutputStream();
                    while ((n = in.read(buf, 0, 1024)) > -1) {
                        baout.write(buf, 0, n);
                    }
                    byte[] bytes = baout.toByteArray();
                    output.setProperty("zip:content/" + entry.getName() + "/jcr:content", "jcr:data", binaryFactory.create(bytes));
                    // all other nt:file properties should be generated by other sequencers (mimetype, encoding,...) but we'll
                    // default them here
                    output.setProperty("zip:content/" + entry.getName() + "/jcr:content", "jcr:encoding", "binary");
                    output.setProperty("zip:content/" + entry.getName() + "/jcr:content",
                                       "jcr:lastModified",
                                       dateFactory.create(entry.getTime()).toString());
                    output.setProperty("zip:content/" + entry.getName() + "/jcr:content",
                                       "jcr:mimeType",
                                       "application/octet-stream");

                }
                in.closeEntry();
                entry = in.getNextEntry();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
