# capacitor-zcs

fds

## Install

```bash
npm install capacitor-zcs
npx cap sync
```

## API

<docgen-index>

* [`getPrinterStatus()`](#getprinterstatus)
* [`openPrinterBox()`](#openprinterbox)
* [`printText(...)`](#printtext)
* [`printBarCode(...)`](#printbarcode)
* [`printQRCode(...)`](#printqrcode)
* [`printImage(...)`](#printimage)
* [`startPrint()`](#startprint)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getPrinterStatus()

```typescript
getPrinterStatus() => Promise<ValueResult<number>>
```

**Returns:** <code>Promise&lt;<a href="#valueresult">ValueResult</a>&lt;number&gt;&gt;</code>

--------------------


### openPrinterBox()

```typescript
openPrinterBox() => Promise<void>
```

--------------------


### printText(...)

```typescript
printText(options: PrintTextOptions) => Promise<void>
```

| Param         | Type                                                          |
| ------------- | ------------------------------------------------------------- |
| **`options`** | <code><a href="#printtextoptions">PrintTextOptions</a></code> |

--------------------


### printBarCode(...)

```typescript
printBarCode(options: PrintBarCodeOptions) => Promise<void>
```

| Param         | Type                                                                |
| ------------- | ------------------------------------------------------------------- |
| **`options`** | <code><a href="#printbarcodeoptions">PrintBarCodeOptions</a></code> |

--------------------


### printQRCode(...)

```typescript
printQRCode(options: PrintQRCodeOptions) => Promise<void>
```

| Param         | Type                                                              |
| ------------- | ----------------------------------------------------------------- |
| **`options`** | <code><a href="#printqrcodeoptions">PrintQRCodeOptions</a></code> |

--------------------


### printImage(...)

```typescript
printImage(options: PrintImageOptions) => Promise<void>
```

| Param         | Type                                                            |
| ------------- | --------------------------------------------------------------- |
| **`options`** | <code><a href="#printimageoptions">PrintImageOptions</a></code> |

--------------------


### startPrint()

```typescript
startPrint() => Promise<void>
```

--------------------


### Interfaces


#### ValueResult

| Prop        | Type           |
| ----------- | -------------- |
| **`value`** | <code>T</code> |


#### PrintTextOptions

| Prop                | Type                                                            |
| ------------------- | --------------------------------------------------------------- |
| **`content`**       | <code>string</code>                                             |
| **`size`**          | <code>number</code>                                             |
| **`underline`**     | <code>boolean</code>                                            |
| **`scaleX`**        | <code>number</code>                                             |
| **`letterSpacing`** | <code>number</code>                                             |
| **`alignment`**     | <code><a href="#zcsprintalignment">ZcsPrintAlignment</a></code> |
| **`style`**         | <code><a href="#zcsprinttextstyle">ZcsPrintTextStyle</a></code> |
| **`font`**          | <code>string</code>                                             |


#### PrintBarCodeOptions

| Prop              | Type                                                                    |
| ----------------- | ----------------------------------------------------------------------- |
| **`content`**     | <code>string</code>                                                     |
| **`width`**       | <code>number</code>                                                     |
| **`height`**      | <code>number</code>                                                     |
| **`displayCode`** | <code>boolean</code>                                                    |
| **`alignment`**   | <code><a href="#zcsprintalignment">ZcsPrintAlignment</a></code>         |
| **`format`**      | <code><a href="#zcsprintbarcodeformat">ZcsPrintBarcodeFormat</a></code> |


#### PrintQRCodeOptions

| Prop            | Type                                                            |
| --------------- | --------------------------------------------------------------- |
| **`content`**   | <code>string</code>                                             |
| **`width`**     | <code>number</code>                                             |
| **`height`**    | <code>number</code>                                             |
| **`alignment`** | <code><a href="#zcsprintalignment">ZcsPrintAlignment</a></code> |


#### PrintImageOptions

| Prop             | Type                                                            |
| ---------------- | --------------------------------------------------------------- |
| **`assetsPath`** | <code>string</code>                                             |
| **`alignment`**  | <code><a href="#zcsprintalignment">ZcsPrintAlignment</a></code> |


### Enums


#### ZcsPrintAlignment

| Members      |
| ------------ |
| **`Left`**   |
| **`Right`**  |
| **`Center`** |


#### ZcsPrintTextStyle

| Members          |
| ---------------- |
| **`Normal`**     |
| **`Bold`**       |
| **`Italic`**     |
| **`BoldItalic`** |


#### ZcsPrintDefinedTextFont

| Members           | Value                       |
| ----------------- | --------------------------- |
| **`Default`**     | <code>'DEFAULT'</code>      |
| **`DefaultBold`** | <code>'DEFAULT_BOLD'</code> |
| **`Monospace`**   | <code>'MONOSPACE'</code>    |
| **`SansSerif`**   | <code>'SANS_SERIF'</code>   |
| **`Serif`**       | <code>'SERIF'</code>        |


#### ZcsPrintBarcodeFormat

| Members                 |
| ----------------------- |
| **`Aztec`**             |
| **`Codabar`**           |
| **`Code_39`**           |
| **`Code_93`**           |
| **`Code_128`**          |
| **`Data_Matrix`**       |
| **`EAN_8`**             |
| **`EAN_13`**            |
| **`ITF`**               |
| **`MAXI_Code`**         |
| **`PDF_417`**           |
| **`QR_Code`**           |
| **`RSS_14`**            |
| **`RSS_Expanded`**      |
| **`UPC_A`**             |
| **`UPC_E`**             |
| **`UPC_EAN_Extension`** |

</docgen-api>
