package com.dasimple.fiscaltrade.plugins.zcs;

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

import org.json.JSONObject;

import java.io.IOException;

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

  @PluginMethod()
  public void getPrinterStatus(PluginCall call) {
    var printer = getPrinter();
    var printerStatus = printer.getPrinterStatus();

    var data = new JSObject();
    data.put("value", printerStatus);
    call.resolve(data);
  }

  @PluginMethod()
  public void openPrinterBox(PluginCall call) {
    var printer = getPrinter();
    var result = printer.openBox();
    if (result != SdkResult.SDK_OK) {
      call.reject("PrintError", result + "");
      return;
    }

    call.resolve();
  }

  @PluginMethod()
  public void print(PluginCall call) {
    var items = call.getArray("items");
    var itemsLength = items.length();
    if (itemsLength == 0) {
      call.resolve();
      return;
    }

    var defaultAlignment = call.hasOption("defaultAlignment") ? parsePrintAlignment(call.getInt("defaultAlignment")) : null;
    var defaultTextFormat = call.getObject("defaultTextFormat");

    var printer = getPrinter();
    var printerStatus = printer.getPrinterStatus();
    switch (printerStatus) {
      case SdkResult.SDK_PRN_STATUS_PAPEROUT:
      case SdkResult.SDK_PRN_STATUS_TOOHEAT:
      case SdkResult.SDK_PRN_STATUS_FAULT:
      case SdkResult.SDK_PRN_STATUS_PRINTING:
      case -1700: /*SdkResult.SDK_PRN_STATUS_DEVMODE*/ {
        call.reject("PrintError", printerStatus + "");
        return;
      }
    }

    PrnStrFormat textFormat = null;

    if (defaultTextFormat != null) {
      textFormat = new PrnStrFormat();

      patchPrintTextFormat(textFormat, defaultTextFormat);

      if (defaultAlignment != null) {
        textFormat.setAli(defaultAlignment);
      }
    }

    for (var i = 0; i < itemsLength; i++) {
      var itemObject = items.opt(i);
      if (itemObject instanceof String content) {
        if (textFormat == null) {
          textFormat = new PrnStrFormat();

          if (defaultAlignment != null) {
            textFormat.setAli(defaultAlignment);
          }
        }

        printer.setPrintAppendString(content, textFormat);
        continue;
      }

      var item = (JSONObject) itemObject;
      var type = item.optString("type", "text");
      var alignment = item.has("alignment") ? parsePrintAlignment(item.optInt("alignment")) : defaultAlignment;

      switch (type) {
        case "text": {
          if (textFormat == null) {
            textFormat = new PrnStrFormat();

            if (defaultAlignment != null) {
              textFormat.setAli(defaultAlignment);
            }
          }

          patchPrintTextFormat(textFormat, item);

          if (alignment != null) {
            textFormat.setAli(alignment);
          }

          var content = item.optString("content");

          printer.setPrintAppendString(content, textFormat);
          break;
        }
        case "barcode": {
          var content = item.optString("content");
          var width = item.optInt("width");
          var height = item.optInt("height");
          var displayCode = item.optBoolean("displayCode");
          var format = BarcodeFormat.values()[item.optInt("format")];

          printer.setPrintAppendBarCode(getContext(), content, width, height, displayCode, alignment, format);
          break;
        }
        case "qrcode": {
          var content = item.optString("content");
          var width = item.optInt("width");
          var height = item.optInt("height");

          printer.setPrintAppendQRCode(content, width, height, alignment);
          break;
        }
        case "image": {
          try {
            var assetsPath = item.optString("assetsPath");
            var inputStream = getActivity().getAssets().open(assetsPath);
            var drawable = Drawable.createFromStream(inputStream, null);
            var bitmap = ((BitmapDrawable) drawable).getBitmap();

            printer.setPrintAppendBitmap(bitmap, alignment);
          } catch (IOException e) {

          }
          break;
        }
      }
    }

    var reverseColor = call.getBoolean("reverseColor", false);
    var result = printer.setPrintStart(reverseColor);
    if (result != SdkResult.SDK_OK) {
      // -1006 - not printed
      call.reject("PrintError", result + "");
      return;
    }

    call.resolve();
  }

  private Printer getPrinter() {
    return DriverManager.getInstance().getPrinter();
  }

  private void patchPrintTextFormat(PrnStrFormat format, JSONObject data) {
    if (data.has("size")) {
      var size = data.optInt("size");
      format.setTextSize(size);
    }

    if (data.has("scaleX")) {
      var scaleX = (float) data.optDouble("scaleX");
      format.setTextScaleX(scaleX);
    }

    if (data.has("letterSpacing")) {
      var letterSpacing = (float) data.optDouble("letterSpacing");
      format.setLetterSpacing(letterSpacing);
    }

    if (data.has("underline")) {
      var underline = data.optBoolean("underline");
      format.setUnderline(underline);
    }

    if (data.has("style")) {
      var index = data.optInt("style");
      format.setStyle(PrnTextStyle.values()[index]);
    }

    if (data.has("font")) {
      var fontNameOrPath = data.optString("font");
      var font = parsePrintTextFont(fontNameOrPath);
      format.setFont(font);

      if (font == PrnTextFont.CUSTOM) {
        format.setPath(fontNameOrPath);
      }
    }
  }
}