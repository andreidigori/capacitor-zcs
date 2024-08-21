import type { Plugin } from '@capacitor/core';

import type { ZcsPrintAlignment } from './enums/zcs-print-alignment.enum';
import type { ZcsPrintBarcodeFormat } from './enums/zcs-print-barcode-format.enum';
import type { ZcsPrintDefinedTextFont } from './enums/zcs-print-defined-text-font.enum';
import type { ZcsPrintTextStyle } from './enums/zcs-print-text-style.enum';

/* Results */

export interface ValueResult<T> {
  value: T;
}

/* Options */

export interface PrintTextOptions {
  content: string;
  size?: number;
  underline?: boolean;
  scaleX?: number;
  letterSpacing?: number;
  alignment?: ZcsPrintAlignment;
  style?: ZcsPrintTextStyle;
  font?: ZcsPrintDefinedTextFont | string; // ZcsPrintDefinedTextFont or custom path
}

export interface PrintBarCodeOptions {
  content: string;
  width: number;
  height: number;
  displayCode: boolean;
  alignment: ZcsPrintAlignment;
  format: ZcsPrintBarcodeFormat;
}

export interface PrintQRCodeOptions {
  content: string;
  width: number;
  height: number;
  alignment: ZcsPrintAlignment;
}

export interface PrintImageOptions {
  assetsPath: string;
  alignment: ZcsPrintAlignment;
}

/* Plugin */

export interface ZcsPlugin extends Plugin {
  getPrinterStatus(): Promise<ValueResult<number>>;
  openPrinterBox(): Promise<void>;
  printText(options: PrintTextOptions): Promise<void>;
  printBarCode(options: PrintBarCodeOptions): Promise<void>;
  printQRCode(options: PrintQRCodeOptions): Promise<void>;
  printImage(options: PrintImageOptions): Promise<void>;
  startPrint(): Promise<void>;
}
