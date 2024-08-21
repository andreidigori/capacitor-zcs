package com.getcapacitor.community.zcs;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.zxing.BarcodeFormat;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;

import java.io.IOException;

@SuppressWarnings("unused")
@CapacitorPlugin(name = "Zcs")
public class ZcsPlugin extends Plugin {

  private static Layout.Alignment parsePrintAlignment(int index) {
    return Layout.Alignment.values()[index];
  }

  private static PrnTextFont parsePrintTextFont(String name) {
    for (var value : PrnTextFont.values()) {
      if (value.toString().equals(name)) {
        return value;
      }
    }
    return PrnTextFont.CUSTOM;
  }

  @SuppressWarnings("unused")
  @PluginMethod()
  public void getPrinterStatus(PluginCall call) {
    var printerStatus = getPrinter().getPrinterStatus();

    var data = new JSObject();
    data.put("value", printerStatus);
    call.resolve(data);
  }

  @SuppressWarnings("unused")
  @PluginMethod()
  public void openPrinterBox(PluginCall call) {
    var result = getPrinter().openBox();
    if (result != SdkResult.SDK_OK) {
      call.reject("Cannot open box.", result + "");
      return;
    }

    call.resolve();
  }

  @SuppressWarnings("unused")
  @PluginMethod()
  public void printText(PluginCall call) {
    var textFormat = new PrnStrFormat();

    var size = call.getInt("size");
    if (size != null) {
      textFormat.setTextSize(size);
    }

    var underline = call.getBoolean("underline");
    if (underline != null) {
      textFormat.setUnderline(underline);
    }

    var scaleX = call.getFloat("scaleX");
    if (scaleX != null) {
      textFormat.setTextScaleX(scaleX);
    }

    var letterSpacing = call.getFloat("letterSpacing");
    if (letterSpacing != null) {
      textFormat.setLetterSpacing(letterSpacing);
    }

    var alignment = call.getInt("alignment");
    if (alignment != null) {
      textFormat.setAli(parsePrintAlignment(alignment));
    }

    var style = call.getInt("style");
    if (style != null) {
      textFormat.setStyle(PrnTextStyle.values()[style]);
    }

    var fontNameOrPath = call.getString("font");
    if (fontNameOrPath != null) {
      var font = parsePrintTextFont(fontNameOrPath);
      textFormat.setFont(font);

      if (font == PrnTextFont.CUSTOM) {
        textFormat.setPath(fontNameOrPath);
      }
    }

    var content = call.getString("content");

    getPrinter().setPrintAppendString(content, textFormat);

    call.resolve();
  }

  @SuppressWarnings("unused")
  @PluginMethod()
  public void printBarCode(PluginCall call) {
    var content = call.getString("content");
    var width = call.getInt("width");
    var height = call.getInt("height");
    var displayCode = call.getBoolean("displayCode");
    var alignment = parsePrintAlignment(call.getInt("alignment"));
    var format = BarcodeFormat.values()[call.getInt("format")];

    getPrinter().setPrintAppendBarCode(getContext(), content, width, height, displayCode, alignment, format);

    call.resolve();
  }


  @SuppressWarnings("unused")
  @PluginMethod()
  public void printQRCode(PluginCall call) {
    var content = call.getString("content");
    var width = call.getInt("width");
    var height = call.getInt("height");
    var alignment = parsePrintAlignment(call.getInt("alignment"));

    getPrinter().setPrintAppendQRCode(content, width, height, alignment);

    call.resolve();
  }


  @SuppressWarnings("unused")
  @PluginMethod()
  public void printImage(PluginCall call) {
    try {
      var assetsPath = call.getString("assetsPath");
      var alignment = parsePrintAlignment(call.getInt("alignment"));
      var inputStream = getActivity().getAssets().open(assetsPath);
      var drawable = Drawable.createFromStream(inputStream, null);
      var bitmap = ((BitmapDrawable) drawable).getBitmap();

      getPrinter().setPrintAppendBitmap(bitmap, alignment);

      call.resolve();
    } catch (IOException e) {
      call.reject(e.getMessage());
    }
  }

  @SuppressWarnings("unused")
  @PluginMethod()
  public void startPrint(PluginCall call) {
    var printerStatus = getPrinter().getPrinterStatus();
    switch (printerStatus) {
      case SdkResult.SDK_PRN_STATUS_PAPEROUT:
      case SdkResult.SDK_PRN_STATUS_TOOHEAT:
      case SdkResult.SDK_PRN_STATUS_FAULT:
      case SdkResult.SDK_PRN_STATUS_PRINTING:
      case -1700: /*SdkResult.SDK_PRN_STATUS_DEVMODE*/ {
        call.reject("Cannot print.", printerStatus + "");
        return;
      }
    }

    var result = getPrinter().setPrintStart();
    if (result != SdkResult.SDK_OK) {
      // -1006 - not printed
      call.reject("Cannot print.", result + "");
      return;
    }

    call.resolve();
  }


  private Printer getPrinter() {
    return DriverManager.getInstance().getPrinter();
  }
}