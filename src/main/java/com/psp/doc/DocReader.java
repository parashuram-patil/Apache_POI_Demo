package com.psp.doc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocReader {

	public static int readDocFile(String fileName) {

		int numOfParaghraphs = 0;

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			HWPFDocument doc = new HWPFDocument(fis);

			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();
			readParahraphs(paragraphs);
			numOfParaghraphs = paragraphs.length;

			fis.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return numOfParaghraphs;

	}

	private static void readParahraphs(String[] paragraphs) {
		System.out.println("************************* Reading .doc file **********************************\n\n");
		int cnt = 1;
		for (String para : paragraphs) {
			cnt = printParagraph(cnt, para);
		}
	}

	private static void readParahraphs(List<XWPFParagraph> paragraphs) {
		System.out.println("************************* Reading .docx file **********************************\n\n");
		for (XWPFParagraph para : paragraphs) {
			printParagraph(paragraphs.indexOf(para), para.getText());
		}
	}

	private static int printParagraph(int cnt, String para) {
		System.out.println( "Paragraph " + cnt++ + " --> " +para.toString() + " \n\n ");
		return cnt;
	}

	public static int readDocxFile(String fileName) {
		int numOfParaghraphs = 0;
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();
			readParahraphs(paragraphs);
			numOfParaghraphs = paragraphs.size();

			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numOfParaghraphs;
	}

	/*public static void main(String[] args) {

		System.out.println(" XXX " + readDocxFile("src/main/resources/sample_docx.docx")); //90

		System.out.println(" XXX " +  readDocFile("src/main/resources/sample_doc.doc")); //133

	}*/

}
