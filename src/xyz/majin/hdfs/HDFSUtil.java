package xyz.majin.hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

public class HDFSUtil {
	FileSystem fs = null;

	@Before
	public void init() throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.242.201");
		//		fs = FileSystem.get(new URI("hdfs://192.168.242.201:9000"),conf,"majin");
		fs = FileSystem.get(conf);
	}

	/**
	 * �ϴ��ļ�
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test
	public void upload() throws IllegalArgumentException, IOException {
		FSDataOutputStream fos = fs.create(new Path("/usr/majin/test/aaa/a.txt"));
		FileInputStream fis = new FileInputStream("G:/qingshu.txt");
		IOUtils.copy(fis, fos);
	}

	/**
	 * �򵥵��ϴ�
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void upload2() throws IllegalArgumentException, IOException {
		fs.copyFromLocalFile(new Path("G:/qingshu.txt"), new Path("/usr/majin/test/qingshu2.txt"));
	}

	/**
	 * ����
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void download() {
		FSDataInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = fs.open(new Path("/usr/majin/test/qingshu.txt"));
			fos = new FileOutputStream("G:/qingshu2.txt");
			IOUtils.copy(fis, fos);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * ������ , ��һ��Ϊʲô���������ǿ��ļ���
	 */
	@Test
	public void download2() {
		try {
			Path srcPath = new Path("/usr/majin/test/qingshu.txt");
			Path dstPath = new Path("G:/download");
			fs.copyToLocalFile(srcPath, dstPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����Ŀ¼
	 */
	@Test
	public void mkdir() throws IllegalArgumentException, Exception {
		fs.mkdirs(new Path("/usr/majin/test/aaa"));
	}

	/**
	 * ɾ��Ŀ¼
	 */
	@Test
	public void rmDir() throws IllegalArgumentException, IOException {
		fs.delete(new Path("/"), true);
	}

	/**
	 *  �����ֻ������Ŀ¼�����е��ļ� ���ݹ飩
	 *
	 */
	@Test
	public void listFiles() throws Exception {
		
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/usr/majin/test"), true);
		while (files.hasNext()) {
			LocatedFileStatus file = files.next();
			Path filePath = file.getPath();
			System.out.println(filePath.getName());
		}

	}
	/**
	 * �г���ǰ�ļ����� �����ж��� ���ļ��к��ļ��������ṩ�ݹ����
	 * 
	 **/
	@Test
	public void listFiles2() throws Exception {
		FileStatus[] files = fs.listStatus(new Path("/usr/majin/test"));
		for (FileStatus file : files) {
			Path filePath = file.getPath();
			System.out.println(filePath.getName()+(file.isFile()?" is file":" is dir"));
		}

	}

}
