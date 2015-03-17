/*
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package pyspark_cassandra.types;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import net.razorvine.pickle.Opcodes;
import net.razorvine.pickle.PickleException;
import net.razorvine.pickle.custom.IObjectPickler;
import net.razorvine.pickle.custom.Pickler;

public class UUIDPickler implements IObjectPickler {
	@Override
	public void pickle(Object o, OutputStream out, Pickler pickler) throws PickleException, IOException {
		UUID uuid = (UUID) o;

		out.write(Opcodes.GLOBAL);
		out.write("uuid\nUUID\n".getBytes());
		out.write(Opcodes.MARK);
		pickler.save(uuid.toString());
		out.write(Opcodes.TUPLE);
		out.write(Opcodes.REDUCE);
	}
}
