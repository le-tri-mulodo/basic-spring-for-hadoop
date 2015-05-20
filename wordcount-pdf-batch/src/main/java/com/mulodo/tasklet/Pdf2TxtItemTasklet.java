/**
 * 
 */
package com.mulodo.tasklet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author TriLe
 */
public class Pdf2TxtItemTasklet implements Tasklet {

	private static final Log LOG = LogFactory.getLog(Pdf2TxtItemTasklet.class);

	private String pdfDir = "";
	private String txtFile = "";

	/**
	 * @param pdfDir
	 *            the pdfDir to set
	 */
	public void setPdfDir(String pdfDir) {
		this.pdfDir = pdfDir;
	}

	/**
	 * @param txtFile
	 *            the txtFile to set
	 */
	public void setTxtFile(String txtFile) {
		this.txtFile = txtFile;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {

		File pdfDirFile = new File(pdfDir);
		StringBuilder sb = new StringBuilder();

		if (null != pdfDirFile.listFiles()) {
			long startTime = System.currentTimeMillis();

			// convert pdf file to text and add content into string builder
			for (File pdfFile : pdfDirFile.listFiles()) {
				sb.append(convertPDF2TXT(pdfFile));
				sb.append("\n");
			}

			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(txtFile, false)))) {
				out.println(sb.toString());
			} catch (IOException e) {
				throw e;
			}

			LOG.info("Convert PDF to TXT [" + pdfDir + "] success. Duration: "
					+ (System.currentTimeMillis() - startTime));
		} else {
			LOG.warn("PDF folder [" + pdfDir + "] is empty");
		}

		return RepeatStatus.FINISHED;
	}

	private String convertPDF2TXT(File pdfFile) throws Exception {

		// Check input is PDF file
		if (!pdfFile.isFile()
				|| !"pdf".equalsIgnoreCase(FilenameUtils.getExtension(pdfFile
						.getName()))) {
			return null;
		}

		System.out.println("++++++++" + pdfFile.getName());

		PDFParser parser = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		PDFTextStripper pdfStripper = null;
		String parsedText = null;

		try {
			parser = new PDFParser(new FileInputStream(pdfFile));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);

		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e) {
				throw e;
			}
		}

		return parsedText;
	}
}
