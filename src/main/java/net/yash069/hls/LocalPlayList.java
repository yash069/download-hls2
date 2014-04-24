/*
 * Copyright (c) YaSH069
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
 package net.yash069.hls;
 
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

 public class LocalPlayList{
	private String LocalFile;
	private List<String> playlist;

	public LocalPlayList(String playFile){
		this.LocalFile = playFile;
		this.playlist = new ArrayList<String>();
	}
	
	public LocalPlayList(){		
	}
	
	public List<String> loadPlayList(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(LocalFile));
			String line;
			
			while((line = reader.readLine()) != null){
				playlist.add(line);
			}

			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return playlist;
	}
	
	public byte[] loadKey(String keyUrl){
		String keyFile = keyUrl.substring( keyUrl.lastIndexOf('/')+1, keyUrl.length() );
		byte[] key = new byte[16];
		try{
			File fp = new File(keyFile);
			if( fp.isFile() ){

				InputStream in = new FileInputStream(fp);
				int read = 0;
				int offset = 0;

				while (offset < 16) {
					if ((read = in.read(key, offset, key.length - offset)) == -1)
						break;

					offset += read;
				}
				in.close();				
			}else{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String response;
				System.out.println("File " + keyFile + " not found, Video can not be decrypted without key\nDo you want to continue?");
				response = reader.readLine();
				if (response.equalsIgnoreCase("no"))
					System.exit(1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return key;
	}
 }
 