/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.portlet.blogsaggregator.blogsentry.viewblogsentryba;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AddBlogsEntryTest extends BaseTestCase {
	public void testAddBlogsEntry() throws Exception {
		int label = 1;

		while (label >= 1) {
			switch (label) {
			case 1:
				selenium.selectWindow("null");
				selenium.selectFrame("relative=top");
				selenium.open("/web/guest/home/");
				selenium.clickAt("link=Blogs Test Page",
					RuntimeVariables.replace("Blogs Test Page"));
				selenium.waitForPageToLoad("30000");
				selenium.clickAt("//input[@value='Add Blog Entry']",
					RuntimeVariables.replace("Add Blog Entry"));
				selenium.waitForPageToLoad("30000");
				selenium.type("//input[@id='_33_title']",
					RuntimeVariables.replace("Blogs Entry Title"));
				selenium.waitForElementPresent(
					"//textarea[@id='_33_editor' and @style='display: none;']");
				selenium.waitForVisible("//span[.='Source']");
				assertEquals(RuntimeVariables.replace("Source"),
					selenium.getText("//span[.='Source']"));
				selenium.clickAt("//span[.='Source']",
					RuntimeVariables.replace("Source"));
				selenium.waitForVisible(
					"//a[@class='cke_button_source cke_on']");
				selenium.waitForVisible(
					"//td[@id='cke_contents__33_editor']/textarea");
				selenium.type("//td[@id='cke_contents__33_editor']/textarea",
					RuntimeVariables.replace("Blogs Entry Content"));
				assertEquals(RuntimeVariables.replace("Source"),
					selenium.getText("//span[.='Source']"));
				selenium.clickAt("//span[.='Source']",
					RuntimeVariables.replace("Source"));
				selenium.waitForElementPresent(
					"//textarea[@id='_33_editor' and @style='display: none;']");
				selenium.waitForVisible(
					"//td[@id='cke_contents__33_editor']/iframe");
				selenium.selectFrame(
					"//td[@id='cke_contents__33_editor']/iframe");
				selenium.waitForText("//body", "Blogs Entry Content");
				selenium.selectFrame("relative=top");

				boolean blogsEntryDescription = selenium.isVisible(
						"//textarea[@id='_33_description']");

				if (blogsEntryDescription) {
					label = 2;

					continue;
				}

				selenium.clickAt("//div[@id='blogsEntryAbstractPanel']/div/div/span",
					RuntimeVariables.replace("Blogs Entry Abstract"));

			case 2:
				selenium.waitForVisible("//textarea[@id='_33_description']");
				selenium.type("//textarea[@id='_33_description']",
					RuntimeVariables.replace("Blogs Entry Description"));
				selenium.clickAt("//input[@value='Publish']",
					RuntimeVariables.replace("Publish"));
				selenium.waitForPageToLoad("30000");
				assertEquals(RuntimeVariables.replace(
						"Your request completed successfully."),
					selenium.getText("//div[@class='portlet-msg-success']"));
				assertEquals(RuntimeVariables.replace("Blogs Entry Title"),
					selenium.getText("//div[@class='entry-title']/h2/a"));
				assertEquals(RuntimeVariables.replace("Blogs Entry Content"),
					selenium.getText("//div[@class='entry-body']/p"));

			case 100:
				label = -1;
			}
		}
	}
}