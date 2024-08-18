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
* [`print(...)`](#print)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)
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


### print(...)

```typescript
print(options: PrintOptions) => Promise<void>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#printoptions">PrintOptions</a></code> |

--------------------


### Interfaces


#### ValueResult

| Prop        | Type           |
| ----------- | -------------- |
| **`value`** | <code>T</code> |


#### PrintOptions

| Prop                    | Type                                                            |
| ----------------------- | --------------------------------------------------------------- |
| **`defaultAlignment`**  | <code><a href="#zcsprintalignment">ZcsPrintAlignment</a></code> |
| **`defaultTextFormat`** | <code><a href="#printtextformat">PrintTextFormat</a></code>     |
| **`reverseColor`**      | <code>boolean</code>                                            |
| **`items`**             | <code>PrintItem[]</code>                                        |


#### PrintTextFormat

| Prop                | Type                                                            |
| ------------------- | --------------------------------------------------------------- |
| **`size`**          | <code>number</code>                                             |
| **`scaleX`**        | <code>number</code>                                             |
| **`letterSpacing`** | <code>number</code>                                             |
| **`underline`**     | <code>boolean</code>                                            |
| **`style`**         | <code><a href="#zcsprinttextstyle">ZcsPrintTextStyle</a></code> |
| **`font`**          | <code>string</code>                                             |


#### PrintTextItem

| Prop          | Type                |
| ------------- | ------------------- |
| **`type`**    | <code>'text'</code> |
| **`content`** | <code>string</code> |


#### PrintBarcodeItem

| Prop              | Type                                                                    |
| ----------------- | ----------------------------------------------------------------------- |
| **`type`**        | <code>'barcode'</code>                                                  |
| **`content`**     | <code>string</code>                                                     |
| **`width`**       | <code>number</code>                                                     |
| **`height`**      | <code>number</code>                                                     |
| **`displayCode`** | <code>boolean</code>                                                    |
| **`format`**      | <code><a href="#zcsprintbarcodeformat">ZcsPrintBarcodeFormat</a></code> |


#### PrintQrcodeItem

| Prop          | Type                  |
| ------------- | --------------------- |
| **`type`**    | <code>'qrcode'</code> |
| **`content`** | <code>string</code>   |
| **`width`**   | <code>number</code>   |
| **`height`**  | <code>number</code>   |


#### PrintImageItem

| Prop             | Type                 |
| ---------------- | -------------------- |
| **`type`**       | <code>'image'</code> |
| **`assetsPath`** | <code>string</code>  |


### Type Aliases


#### PrintItem

<code><a href="#printtextitem">PrintTextItem</a> | <a href="#printbarcodeitem">PrintBarcodeItem</a> | <a href="#printqrcodeitem">PrintQrcodeItem</a> | <a href="#printimageitem">PrintImageItem</a> | string</code>


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
