import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import com.mpatric.mp3agic.Mp3File;

public class Mp3TimeUtilTEst {

	public static void main(String[] args) throws  Exception {

		String filePath = "E://zhiyin//内容相关//new";
//		filePath = "E://zhiyin//内容相关//MP3-48";
		filePath = "E://opt";
		filePath = "E:/zhiyin/内容相关/MP3renderNew";
		filePath = "E:/BaiduMusic/Songs";
		File dir = new File(filePath);
		File[] fs = dir.listFiles();

		List<File> fileList = Arrays.asList(fs);

		for (File tmp : fileList) {

			System.out.println(tmp.length()/1024);
			
			// 方法1
			 MP3File f = (MP3File)AudioFileIO.read(tmp);
			 MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
			 System.out.println(tmp.getName() + "  "
			 +audioHeader.getTrackLength());
			
			 // 方法2
			 Mp3File mp3file = new Mp3File(tmp.getPath());
			 System.out.println("Length of this mp3 is: " +
			 mp3file.getLengthInSeconds() + " seconds");
		}

	}

}
