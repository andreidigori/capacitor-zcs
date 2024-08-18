import type { Plugin } from '@capacitor/core';

import type { ZcsPrintAlignment } from './enums/zcs-print-alignment.enum';
import type { ZcsPrintBarcodeFormat } from './enums/zcs-print-barcode-format.enum';
import type { ZcsPrintDefinedTextFont } from './enums/zcs-print-defined-text-font.enum';
import type { ZcsPrintTextStyle } from './enums/zcs-print-text-style.enum';

/* Utils */

export interface PrintTextFormat {
  size?: number;
  scaleX?: number;
  letterSpacing?: number;
  underline?: boolean;
  style?: ZcsPrintTextStyle;
  font?: ZcsPrintDefinedTextFont | string; // ZcsPrintDefinedTextFont or custom path
}

export interface BasePrintItem {
  alignment?: ZcsPrintAlignment;
}

export interface PrintTextItem extends BasePrintItem, PrintTextFormat {
  type?: 'text';
  content: string;
}

export interface PrintBarcodeItem extends BasePrintItem {
  type: 'barcode';
  content: string;
  width: number;
  height: number;
  displayCode: boolean;
  format: ZcsPrintBarcodeFormat;
}

export interface PrintQrcodeItem extends BasePrintItem {
  type: 'qrcode';
  content: string;
  width: number;
  height: number;
}

export interface PrintImageItem extends BasePrintItem {
  type: 'image';
  assetsPath: string;
}

export type PrintItem = PrintTextItem | PrintBarcodeItem | PrintQrcodeItem | PrintImageItem | string;

/* Results */

export interface ValueResult<T> {
  value: T;
}

/* Options */

export interface PrintOptions {
  defaultAlignment?: ZcsPrintAlignment;
  defaultTextFormat?: PrintTextFormat;
  reverseColor?: boolean;
  items: PrintItem[];
}

/* Plugin */

export interface ZcsPlugin extends Plugin {
  getPrinterStatus(): Promise<ValueResult<number>>;
  openPrinterBox(): Promise<void>;
  print(options: PrintOptions): Promise<void>;
}
