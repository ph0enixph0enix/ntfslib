/**
 * Copyright © 2014 Jan Seeger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.alphadev.ntfslib;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import net.alphadev.ntfslib.api.BlockDevice;

public class BootSector {
    public BootSector(BlockDevice device) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(512);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        device.read(0, bb);

        if ((bb.get(510) & 0xff) != 0x55 ||
                (bb.get(511) & 0xff) != 0xaa) throw new IOException(
                "missing boot sector signature");
    }
}