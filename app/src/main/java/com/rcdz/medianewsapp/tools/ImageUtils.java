package com.rcdz.medianewsapp.tools;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


/**
 * 拍照/相册选取/图片压缩
 *
 * @author yaozu
 */
public class ImageUtils {

	/**
	 * 保存文件
	 * @param bm
	 * @throws IOException
	 */
	public static String saveFile(Bitmap bm) throws IOException {
		String path= Environment.getExternalStorageDirectory()+"/temp/"+ Math.abs(Math.random())+".jpg";
		String path2= Environment.getExternalStorageDirectory()+"/temp";
		if(!new File(path2).exists()){
			new File(path2).mkdirs();
		}
		File myCaptureFile = new File(path);
		if(!myCaptureFile.exists()){
			myCaptureFile.createNewFile();
		}
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		bos.flush();
		bos.close();
		return path;
	}
	public static String saveToInternalStorage(Context context, Uri tempUri) {
		InputStream in = null;
		OutputStream out = null;
		File sourceExternalImageFile = new File(tempUri.getPath());
		File destinationInternalImageFile = new File(
				getOutputInternalMediaFile(context, 1).getPath());
		try {
			destinationInternalImageFile.createNewFile();
			in = new FileInputStream(sourceExternalImageFile);
			out = new FileOutputStream(destinationInternalImageFile);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					in.close();
				}
			} catch (IOException e) {

			}
		}
		return destinationInternalImageFile.getPath();
	}



	private static File getOutputInternalMediaFile(Context context, int type) {
		File mediaStorageDir = new File(context.getFilesDir(),
				"myInternalPicturesDir");
		createMediaStorageDir(mediaStorageDir);
		return createFile(type, mediaStorageDir);
	}
	private static void createMediaStorageDir(File mediaStorageDir)
	{
		if (!mediaStorageDir.exists()) {
			mediaStorageDir.mkdirs();
		}
	}
	private static File createFile(int type, File mediaStorageDir)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile = null;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		}
		return mediaFile;
	}

	public static final int NONE = 0;
	public static final String IMAGE_UNSPECIFIED = "image/*";//任意图片类型
	public static final int PHOTOGRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	public static final int GET_IMAGE_BY_CAMERA = 5001;
	public static final int GET_IMAGE_FROM_PHONE = 5002;
	public static final int CROP_IMAGE = 5003;
	private static final String TAG = ImageUtils.class.getSimpleName();
	public static Uri imageUriFromCamera;
	public static Uri cropImageUri;
	public static String imageName;

	/**
	 * 拍照
	 *
	 * @param activity
	 */
	public static void openCameraImage(final Activity activity, int code) {
		ImageUtils.imageUriFromCamera = ImageUtils.createImagePathUri(activity);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// MediaStore.EXTRA_OUTPUT参数不设置时,系统会自动生成一个uri,但是只会返回一个缩略图
		// 返回图片在onActivityResult中通过以下代码获取
		// Bitmap bitmap = (Bitmap) data.getExtras().get("data");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUtils.imageUriFromCamera);
		activity.startActivityForResult(intent, code);
	}

	/**
	 * 获取本地图片
	 *
	 * @param activity
	 */
	public static void openLocalImage(final Activity activity, int code) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_PICK);
		activity.startActivityForResult(intent, code);
	}

	/**
	 * 图片不裁剪
	 *
	 * @param activity
	 * @param uri      原图的地址
	 * @param destUri  剪辑后的图片存放地址
	 */
	public static void startPhotoZoom(Activity activity, Uri uri, Uri destUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("return-data", false);//如果设为true则返回bitmap
		intent.putExtra(MediaStore.EXTRA_OUTPUT, destUri);//输出文件
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		activity.startActivityForResult(intent, PHOTORESOULT);
	}

	/**
	 * 剪辑图片的大小
	 * 默认输出 150*150
	 *
	 * @param activity
	 * @param srcUri
	 */
	public static void cropImage(Activity activity, Uri srcUri) {
		/**
		 * 应对三星设备图片拍照默认横屏的问题处理
		 */
		/*String path = getRealPathByURI(srcUri, fragment.getActivity());
		int degree = readBitmapDegree(path);
		Bitmap bm = rotateBitmapByDegree(BitmapFactory.decodeFile(path), degree);
		saveBitmapToLocal(bm, path);*/


		ImageUtils.cropImageUri = ImageUtils.createImagePathUri(activity);

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(srcUri, "image/*");
		intent.putExtra("crop", "true");

		// //////////////////////////////////////////////////////////////
		// 1.宽高和比例都不设置时,裁剪框可以自行调整(比例和大小都可以随意调整)
		// //////////////////////////////////////////////////////////////
		// 2.只设置裁剪框宽高比(aspect)后,裁剪框比例固定不可调整,只能调整大小
		// //////////////////////////////////////////////////////////////
		// 3.裁剪后生成图片宽高(output)的设置和裁剪框无关,只决定最终生成图片大小
		// //////////////////////////////////////////////////////////////
		// 4.裁剪框宽高比例(aspect)可以和裁剪后生成图片比例(output)不同,此时,
		// 会以裁剪框的宽为准,按照裁剪宽高比例生成一个图片,该图和框选部分可能不同,
		// 不同的情况可能是截取框选的一部分,也可能超出框选部分,向下延伸补足
		// //////////////////////////////////////////////////////////////

		// aspectX aspectY 是裁剪框宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪后生成图片的宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("scale", true);
		// return-data为true时,会直接返回bitmap数据,但是大图裁剪时会出现问题,推荐下面为false时的方式
		// return-data为false时,不会返回bitmap,但需要指定一个MediaStore.EXTRA_OUTPUT保存图片uri
		intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUtils.cropImageUri);
		intent.putExtra("return-data", false);

		activity.startActivityForResult(intent, CROP_IMAGE);
	}

	/**
	 * 根据尺寸剪辑图片
	 *
	 * @param activity
	 * @param srcUri
	 * @param height
	 * @param wdith
	 */
	public static void cropImageBySize(Activity activity, Uri srcUri, int height, int wdith) {
		ImageUtils.cropImageUri = ImageUtils.createImagePathUri(activity);

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(srcUri, "image/*");
		intent.putExtra("crop", "true");

		// aspectX aspectY 是裁剪框宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪后生成图片的宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("scale", true);
		// return-data为true时,会直接返回bitmap数据,但是大图裁剪时会出现问题,推荐下面为false时的方式
		// return-data为false时,不会返回bitmap,但需要指定一个MediaStore.EXTRA_OUTPUT保存图片uri
		intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUtils.cropImageUri);
		intent.putExtra("return-data", false);

		activity.startActivityForResult(intent, CROP_IMAGE);
	}

	/**
	 * 根据Uri获取路径
	 *
	 * @param contentUri
	 * @return
	 */
	public static String getRealPathByURI(Uri contentUri, Context context) {
		String res = null;
		String[] proj = {MediaStore.Images.Media.DATA};
		Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
		if (cursor.moveToFirst()) {
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			res = cursor.getString(column_index);
		}
		cursor.close();
		return res;
	}

	/**
	 * 创建一条图片地址uri,用于保存拍照后的照片
	 *
	 * @param context
	 * @return 图片的uri
	 */
	public static Uri createImagePathUri(Context context) {
		Uri imageFilePath = null;
		String status = Environment.getExternalStorageState();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
		long time = System.currentTimeMillis();
		String imageName = timeFormatter.format(new Date(time));
		// ContentValues是我们希望这条记录被创建时包含的数据信息
		ContentValues values = new ContentValues(9);
		values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
		values.put(MediaStore.Images.Media.DATE_TAKEN, time);
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
		if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
			imageFilePath = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		} else {
			imageFilePath = context.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
		}
		String path = getRealPathByURI(imageFilePath,context);//获取拍照后的照片路径
		Bitmap bitmap= getImageBitmap(path);//根据路径获取照片
		saveBitmap(path,bitmap);//将压缩后的图片保存在原路径

		Log.i("test", "生成的照片输出路径：" + imageFilePath.toString());
		return imageFilePath;
	}

	/**
	 * 制作图片的路径地址
	 *
	 * @param context
	 * @return
	 */
	public static String getPath(Context context) {
		String path = null;
		File file = null;
		long tag = System.currentTimeMillis();
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			//SDCard是否可用
			path = Environment.getExternalStorageDirectory() + File.separator + "myimages/";
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			path = Environment.getExternalStorageDirectory() + File.separator + "myimages/" + tag + ".png";
		} else {
			path = context.getFilesDir() + File.separator + "myimages/";
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			path = context.getFilesDir() + File.separator + "myimages/" + tag + ".png";
		}
		return path;
	}


	/**
	 * 图片压缩
	 */
	public static void compressBmpToFile(File file, int height, int width) {
		Bitmap bmp = decodeSampledBitmapFromFile(file.getPath(), height, width);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
		/*while (baos.toByteArray().length / 1024 > 30) {
			baos.reset();
			if (options - 10 > 0) {
				options = options - 10;
				bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
			}
			if (options - 10 <= 0) {
				break;
			}
		}*/
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取图片的Uri
	 *
	 * @param context
	 * @param path
	 * @return
	 */
	public static Uri getUri(Context context, String path) {
		Uri uri = null;
		File file = new File(path);
		uri = Uri.fromFile(file);
		return uri;
	}

	/**
	 * 将图片变成bitmap
	 *
	 * @param path
	 * @return
	 */
	public static Bitmap getImageBitmap(String path) {
		Bitmap bitmap = null;
		File file = new File(path);
		if (file.exists()) {
			bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}
		return null;
	}

//=================================图片压缩方法===============================================

	/**
	 * 质量压缩
	 *
	 * @param image
	 * @param maxkb
	 * @return
	 * @author ping 2015-1-5 下午1:29:58
	 */
	public static Bitmap compressBitmap(Bitmap image, int maxkb) {
		//L.showlog(压缩图片);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
		int options = 100;
		// 循环判断如果压缩后图片是否大于(maxkb)50kb,大于继续压缩
		while (baos.toByteArray().length / 1024 > maxkb) {
			// 重置baos即清空baos
			baos.reset();
			if (options - 10 > 0) {
				// 每次都减少10
				options -= 10;
			}
			// 这里压缩options%，把压缩后的数据存放到baos中
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		// 把压缩后的数据baos存放到ByteArrayInputStream中
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		// 把ByteArrayInputStream数据生成图片
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}


	/**
	 * @param image
	 * @return
	 */
	public static Bitmap compressBitmap(Bitmap image) {
		//L.showlog(压缩图片);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		// 把ByteArrayInputStream数据生成图片
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	/**
	 * @param res
	 * @param resId
	 * @param reqWidth  所需图片压缩尺寸最小宽度
	 * @param reqHeight 所需图片压缩尺寸最小高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * @param filepath  图片路径
	 * @param reqWidth  所需图片压缩尺寸最小宽度
	 * @param reqHeight 所需图片压缩尺寸最小高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromFile(String filepath, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filepath, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filepath, options);
	}

	/**
	 * @param bitmap
	 * @param reqWidth  所需图片压缩尺寸最小宽度
	 * @param reqHeight 所需图片压缩尺寸最小高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromBitmap(Bitmap bitmap,
                                                       int reqWidth, int reqHeight) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
		byte[] data = baos.toByteArray();

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}

	/**
	 * 计算压缩比例值(改进版 by touch_ping)
	 * <p>
	 * 原版2>4>8...倍压缩
	 * 当前2>3>4...倍压缩
	 *
	 * @param options   解析图片的配置信息
	 * @param reqWidth  所需图片压缩尺寸最小宽度O
	 * @param reqHeight 所需图片压缩尺寸最小高度
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

		final int picheight = options.outHeight;
		final int picwidth = options.outWidth;

		int targetheight = picheight;
		int targetwidth = picwidth;
		int inSampleSize = 1;

		if (targetheight > reqHeight || targetwidth > reqWidth) {
			while (targetheight >= reqHeight
					&& targetwidth >= reqWidth) {
				inSampleSize += 1;
				targetheight = picheight / inSampleSize;
				targetwidth = picwidth / inSampleSize;
			}
		}

		Log.i("===", "最终压缩比例:" + inSampleSize + "倍");
		Log.i("===", "新尺寸:" + targetwidth + "*" + targetheight);
		return inSampleSize;
	}

	// 读取图像的旋转度
	public static int readBitmapDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}


	/**
	 * 将图片按照某个角度进行旋转
	 *
	 * @param bm     需要旋转的图片
	 * @param degree 旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
		Bitmap returnBm = null;

		// 根据旋转角度，生成旋转矩阵
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		try {
			// 将原始图片按照旋转矩阵进行旋转，并得到新的图片
			returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError e) {
		}
		if (returnBm == null) {
			returnBm = bm;
		}
		if (bm != returnBm) {
			bm.recycle();
		}
		return returnBm;
	}

	/**
	 * @param mBitmap
	 * @param fileName
	 */
	public static void saveBitmapToLocal(Bitmap mBitmap, String fileName) {
		if (mBitmap != null) {
			FileOutputStream fos = null;
			try {
				File file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				fos = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 将下载下来的图片保存到SD卡或者本地.并返回图片的路径(包括文件命和扩展名)
	 *
	 * @param context
	 * @param bitName
	 * @param mBitmap
	 * @return
	 */
	public static String saveBitmap(Context context, String bitName, Bitmap mBitmap) {
		String path = null;
		File f;
		if (mBitmap != null) {
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				f = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM/Camera/");
				String fileName = Environment.getExternalStorageDirectory() + File.separator + "DCIM/Camera/" + bitName + ".png";
				path = fileName;
				FileOutputStream fos = null;
				try {
					if (!f.exists()) {
						f.mkdirs();
					}
					File file = new File(fileName);
					file.createNewFile();
					fos = new FileOutputStream(file);
					mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {
				//本地存储路径
				f = new File(context.getFilesDir() + File.separator + "DCIM/Camera/");
				Log.i(TAG, "本地存储路径:" + context.getFilesDir() + File.separator + "DCIM/Camera/" + bitName + ".png");
				path = context.getFilesDir() + File.separator + "DCIM/Camera/" + bitName + ".png";
				FileOutputStream fos = null;
				try {
					if (!f.exists()) {
						f.mkdirs();
					}
					File file = new File(path);
					file.createNewFile();
					fos = new FileOutputStream(file);
					mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

		return path;
	}


	/**
	 * 得到bitmap的path，传入context是因为有些手机没有外部存储，得做一下判断
	 *
	 * @param context
	 * @return
	 */
	public static String getBitmapPath(Context context) {

		String path;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			path = Environment.getExternalStorageDirectory() + File.separator + "rcdownload/";
		} else {
			path = context.getFilesDir() + File.separator + "rcdownload/";
		}
		return path;
	}

	/**
	 * preImage
	 *
	 * @param fileName
	 * @param mBitmap
	 * @return
	 */
	public static boolean saveBitmap(String fileName, Bitmap mBitmap) {
		File f;
		if (mBitmap != null) {
			f = new File(fileName);
			FileOutputStream fos = null;
			try {
				if (!f.exists()) {
					f.mkdirs();
				}
				File file = new File(fileName);
				file.createNewFile();
				fos = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * 删除图片
	 *
	 * @param path
	 * @param bitName
	 */
	public static void deleteImage(String path, String bitName) {
		File dirFile = new File(path + bitName + ".jpg");
		if (!dirFile.exists()) {
			return;
		}
		dirFile.delete();
	}


	/**
	 * 删除图片
	 *
	 * @param context
	 * @param bitName
	 */
	public void deleteFile(Context context, String bitName) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File dirFile = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM/Camera/" + bitName + ".png");
			if (!dirFile.exists()) {
				return;
			}

			dirFile.delete();
		} else {
			File f = new File(context.getFilesDir() + File.separator
					+ "DCIM/Camera/" + bitName + ".png");
			if (!f.exists()) {
				return;
			}
			f.delete();
		}
	}


	//拆解byte的数据包
	public static ArrayList<byte[]> SplitDataPackage(byte[] bitmap) {

		ArrayList<byte[]> resault = new ArrayList<byte[]>();

		int length;
		while (bytesToInt2(bitmap, 0) != 0) {
			length = bytesToInt2(bitmap, 0);//当前内容的长度
			byte[] b = new byte[length];
			System.arraycopy(bitmap, 4, b, 0, length);//生成当前内容的byte[]
			resault.add(b);
			byte[] tempbytes = new byte[bitmap.length - length - 4];//在总byte[]中去除当前长度后的空byte[]
			System.arraycopy(bitmap, 4 + length, tempbytes, 0, bitmap.length - length - 4);//获取剩余的总byte[]
			bitmap = tempbytes;
		}
		return resault;

	}

	//bytesToInt2byte[]转int
	public static int bytesToInt2(byte[] src, int offset) {
		if (src.length == 0) {
			return 0;

		} else {
			int value;
			value = (int) ((src[offset] & 0xFF)
					| ((src[offset + 1] & 0xFF) << 8)
					| ((src[offset + 2] & 0xFF) << 16)
					| ((src[offset + 3] & 0xFF) << 24));
			return value;
		}
	}

	//byte[]转化为bitmap
	public static Bitmap getBitmapFromByte(byte[] temp) {
		if (temp != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return bitmap;
		} else {
			return null;
		}
	}
	//bitmap转化为byte[]
	public static byte[] Bitmap2Bytes(Bitmap bm){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}



}