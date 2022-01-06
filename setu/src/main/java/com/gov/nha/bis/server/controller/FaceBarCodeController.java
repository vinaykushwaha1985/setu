package com.gov.nha.bis.server.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.util.TransactionManager;

@CrossOrigin
@Controller
public class FaceBarCodeController extends NhaBisBaseController {


	public static final String FILE_TYPE = "jpeg";

	private static final Logger logger = LoggerFactory.getLogger(FaceBarCodeController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@RequestMapping(value="/faceId",method = RequestMethod.GET)
	public void getKycFaceId(){

		response.setHeader( "Cache-Control", "no-store" );
		response.setHeader( "Pragma", "no-cache" );
		response.setDateHeader( "Expires", 0 );
		HttpSession session=null;
		String qrCodeText="";
		String userId="";
		try {

			session = request.getSession(true);
			
			String faceId=TransactionManager.getFaceIdTransactionId();
			
			userId=(String)session.getAttribute("USERID");
			
			qrCodeText =getBarCodeText(faceId,userId);

			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();

			BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 120, 120, hintMap);
			int matrixWidth = byteMatrix.getWidth();

			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}


			
			session.setAttribute("FACEID", faceId);

			logger.info("FACEID==="+faceId);

			OutputStream outputStream = response.getOutputStream();

			ImageIO.write(image, FILE_TYPE, outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value="/AuthfaceId",method = RequestMethod.GET)
	public void getAuthfaceId(){

		response.setHeader( "Cache-Control", "no-store" );
		response.setHeader( "Pragma", "no-cache" );
		response.setDateHeader( "Expires", 0 );
		HttpSession session=null;
		String qrCodeText="";
		String userId="";
		try {

			session = request.getSession(true);
			
			String faceId=TransactionManager.getFaceIdTransactionId();
			
			userId=(String)session.getAttribute("USERID");
			
			qrCodeText =getBarCodeText(faceId,userId);

			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();

			BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 120, 120, hintMap);
			int matrixWidth = byteMatrix.getWidth();

			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}


			
			session.setAttribute("AUTH_FACEID", faceId);

			logger.info("AUTH_FACEID==="+faceId);

			OutputStream outputStream = response.getOutputStream();

			ImageIO.write(image, FILE_TYPE, outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getBarCodeText(String faceId,String userId) {
		// TODO Auto-generated method stub

		JSONObject res= new JSONObject();
    	res.put("FACE_ID", faceId);
    	res.put("RETURN_URL", applicationConstantConfig.FACE_ID_BARCODE_GEN_URL);
    	res.put("USER_ID", userId);
	
		return res.toString();
	}

	

}
