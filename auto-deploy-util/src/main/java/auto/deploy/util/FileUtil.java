package auto.deploy.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @描述：文件工具类
 *
 * @作者：zhongjy
 * 
 * @时间：2017年7月6日 上午10:28:29
 */
public class FileUtil {

	private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 
	 * @描述：把字符串保存到文件（第二个参数是制定文件名）.
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月6日 上午10:36:23
	 */
	public static void str2file(String data, String fileName) {
		FileWriter fileWritter = null;
		BufferedWriter bufferWritter = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			// true-末尾追加，false-覆盖原有
			fileWritter = new FileWriter(file, false);
			bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			// 关闭bufferWritter
			if (bufferWritter != null) {
				try {
					bufferWritter.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
	}

	/**
	 * 
	 * @描述：读取文件中的字符串并返回字符串
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月6日 上午10:34:54
	 */
	public static String file2str(String filePath) {

		return null;
	}

	/**
	 *
	 * @描述：根据输入流上传文件
	 *
	 * @返回：File
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年8月17日
	 */
	public static File saveFileFromInputStream(InputStream stream, String path, String filename) {
		// 检查保存上传文件的文件夹是否存在 不存在则创建
		File dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File file = null;
		FileOutputStream fs = null;
		try {
			file = new File(path + File.separator + filename);
			fs = new FileOutputStream(file);
			// 读取输入流中的文件信息到输出流中
			byte[] buffer = new byte[stream.available()];
			// int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				// bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return file;
	}

	/**
	 * @描述：根据路径删除单个文件
	 *
	 * @返回：boolean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年8月18日
	 */
	public static void deleteFile(String sPath) {
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * @描述：根据文件对象删除单个文件
	 *
	 * @返回：boolean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年8月18日
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		}
	}

	/**
	 * @描述：格式化文件路径
	 *
	 * @返回：string
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年09月08日
	 */
	public static String formatPath(String path) {
		String newPath = "";
		if (path.indexOf("/") > -1) {
			String[] strs = path.split("/");
			if (strs.length > 0) {
				for (String str : strs) {
					newPath += str + File.separator + File.separator;
				}
			}
		}

		if (path.indexOf("//") > -1) {
			String[] strs = path.split("//");
			if (strs.length > 0) {
				for (String str : strs) {
					newPath += str + File.separator + File.separator;
				}
			}
		}

		if (path.indexOf("\\") > -1) {
			String[] strs = path.split("/\\/");
			if (strs.length > 0) {
				for (String str : strs) {
					newPath += str + File.separator + File.separator;
				}
			}
		}

		return newPath;
	}

	/**
	 * @描述：拷贝文件到指定路径
	 *
	 * @返回：string
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年09月08日
	 */
	public static File Copy(File oldfile, String newPath, String fileName) {
		File newFile = null;
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			// int bytesum = 0;
			int byteread = 0;
			if (oldfile.exists()) {
				inStream = new FileInputStream(oldfile);
				File tempPath = new File(newPath);
				if (!tempPath.exists()) {
					tempPath.mkdirs();
				}

				newFile = new File(newPath + File.separator + File.separator + fileName);
				fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[inStream.available()];
				while ((byteread = inStream.read(buffer)) != -1) {
					// bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("error  ");
			logger.error("", e);
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return newFile;
	}

	public static final int SIZETYPE_B = 1;// 获取文件大小单位为B
	public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB
	public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB
	public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB

	/**
	 * @描述：转换文件大小为指定单位 sizeType 1 --> B 2 --> KB 3 --> MB 4 --> GB
	 *
	 * @返回：int
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年09月08日
	 */
	public static int formatFileSize(long fileSize, int sizeType) {
		DecimalFormat df = new DecimalFormat("#");
		int fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Integer.valueOf(df.format((int) fileSize));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Integer.valueOf(df.format((int) fileSize / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Integer.valueOf(df.format((int) fileSize / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Integer.valueOf(df.format((int) fileSize / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}

	/**
	 * @描述：转换文件大小为指定单位 sizeType 1 --> B 2 --> KB 3 --> MB 4 --> GB
	 *
	 * @返回：double
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年09月08日
	 */
	public static double fomatFileSize(long fileS, int sizeType) {
		DecimalFormat df = new DecimalFormat("#");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}

	public static void main(String[] args) throws Exception {

	}
}
