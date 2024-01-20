package com.filecard.multiplatform

import android.nfc.Tag
import android.os.AsyncTask
import android.util.Log
import com.st.st25android.AndroidReaderInterface
import com.st.st25sdk.Helper
import com.st.st25sdk.NFCTag
import com.st.st25sdk.NFCTag.NfcTagTypes
import com.st.st25sdk.STException
import com.st.st25sdk.TagHelper
import com.st.st25sdk.TagHelper.ProductID
import com.st.st25sdk.iso14443b.Iso14443bTag
import com.st.st25sdk.type2.Type2Tag
import com.st.st25sdk.type2.st25tn.ST25TNTag
import com.st.st25sdk.type4a.Type4Tag
import com.st.st25sdk.type4a.m24srtahighdensity.M24SR02KTag
import com.st.st25sdk.type4a.m24srtahighdensity.M24SR04KTag
import com.st.st25sdk.type4a.m24srtahighdensity.M24SR16KTag
import com.st.st25sdk.type4a.m24srtahighdensity.M24SR64KTag
import com.st.st25sdk.type4a.m24srtahighdensity.ST25TA16KTag
import com.st.st25sdk.type4a.m24srtahighdensity.ST25TA64KTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KBDTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KBPTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KBTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KDTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KPTag
import com.st.st25sdk.type4a.st25ta.ST25TA02KTag
import com.st.st25sdk.type4a.st25ta.ST25TA512BTag
import com.st.st25sdk.type4a.st25ta.ST25TA512Tag
import com.st.st25sdk.type4b.Type4bTag
import com.st.st25sdk.type5.STType5Tag
import com.st.st25sdk.type5.Type5Tag
import com.st.st25sdk.type5.lri.LRi1KTag
import com.st.st25sdk.type5.lri.LRi2KTag
import com.st.st25sdk.type5.lri.LRi512Tag
import com.st.st25sdk.type5.lri.LRiS2KTag
import com.st.st25sdk.type5.m24lr.LRiS64KTag
import com.st.st25sdk.type5.m24lr.M24LR04KTag
import com.st.st25sdk.type5.m24lr.M24LR16KTag
import com.st.st25sdk.type5.m24lr.M24LR64KTag
import com.st.st25sdk.type5.st25dv.ST25DVCTag
import com.st.st25sdk.type5.st25dv.ST25DVTag
import com.st.st25sdk.type5.st25dv.ST25TV04KPTag
import com.st.st25sdk.type5.st25dv.ST25TV16KTag
import com.st.st25sdk.type5.st25dv.ST25TV64KTag
import com.st.st25sdk.type5.st25dvpwm.ST25DV02KW1Tag
import com.st.st25sdk.type5.st25dvpwm.ST25DV02KW2Tag
import com.st.st25sdk.type5.st25tv.ST25TVTag
import com.st.st25sdk.type5.st25tvc.ST25TVCTag

/**
 * Class creating a Asynchronous Task performing the discovery of a Tag.
 *
 * The input data is an 'android.nfc.Tag' (called 'androidTag' in the code below).
 *
 * Some NFC commands are executed to identify more precisely the product.
 * A ST25SDK NFCTag is then instantiated.
 * This NFCTag can be used by the SDK to communicate with the tag.
 */
class TagDiscovery
/**
 * Constuctor of TagDiscovery class.
 * The requester of the discovery should implement the 'onTagDiscoveryCompletedListener' Listener.
 * TagDiscovery will use this Listener to notify the requester when the discovery is finished.
 *
 * @param listener Method that will be called when the TagDiscovery is finished.
 */(private val mListener: onTagDiscoveryCompletedListener) :
    AsyncTask<Tag?, Void?, STException?>() {
    // NFC Tag as defined by Android
    private var mAndroidTag: Tag? = null
    private var mProductID: ProductID? = null

    // NFC Tag as defined in ST25 SDK
    private var mNfcTag: NFCTag? = null

    interface onTagDiscoveryCompletedListener {
        fun onTagDiscoveryCompleted(nfcTag: NFCTag?, productId: ProductID?, e: STException?)
    }

    /**
     * Class used to store the information about the tag taped. It contains:
     * - the NFCTag object
     * - the ProductID
     */
    class TagInfo {
        var nfcTag: NFCTag? = null
        var productID: ProductID? = null
    }

    override fun doInBackground(vararg params: Tag?): STException? {
            mAndroidTag = params[0]
            val tagInfo: TagInfo?
            try {
                tagInfo = performTagDiscovery(mAndroidTag)
            } catch (e: STException) {
                mNfcTag = null
                mProductID = ProductID.PRODUCT_UNKNOWN
                return e
            }
            mNfcTag = tagInfo!!.nfcTag
            mProductID = tagInfo.productID
            return null

    }

    override fun onPostExecute(result: STException?) {
        // Code executed on UI Thread

        // Notify the Listener that a tag has been found
        mListener.onTagDiscoveryCompleted(mNfcTag, mProductID, result)
    }

    companion object {
        const val TAG = "TagDiscovery"
        ////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * This function performs the discovery of a tag (provided as an Android 'Tag' class).
         * It returns a TagInfo class containing the nfcTag (as defined by the SDK) and the ProductId.
         *
         * IMPORTANT: This function will fail if it is called from UI Thread.
         *
         * @param androidTag Tag as defined by Android ('android.nfc.Tag')
         * @return TagInfo or null if the discovery failed.
         */
        @Throws(STException::class)
        fun performTagDiscovery(androidTag: Tag?): TagInfo? {
            val tagInfo = TagInfo()
            tagInfo.nfcTag = null
            tagInfo.productID = ProductID.PRODUCT_UNKNOWN
            Log.v(TAG, "Starting TagDiscovery")
            if (androidTag == null) {
                Log.e(TAG, "androidTag cannot be null!")
                return null
            }
            val readerInterface = AndroidReaderInterface.newInstance(androidTag)
            if (readerInterface == null) {
                tagInfo.nfcTag = null
                tagInfo.productID = ProductID.PRODUCT_UNKNOWN
                return tagInfo
            }
            var uid = androidTag.id
            when (readerInterface.mTagType) {
                NfcTagTypes.NFC_TAG_TYPE_V -> {
                    uid = Helper.reverseByteArray(uid)
                    tagInfo.productID = TagHelper.identifyTypeVProduct(readerInterface, uid)
                }

                NfcTagTypes.NFC_TAG_TYPE_4A -> tagInfo.productID =
                    TagHelper.identifyType4Product(readerInterface, uid)

                NfcTagTypes.NFC_TAG_TYPE_2 -> tagInfo.productID =
                    TagHelper.identifyIso14443aType2Type4aProduct(readerInterface, uid)

                NfcTagTypes.NFC_TAG_TYPE_4B -> tagInfo.productID =
                    TagHelper.identifyIso14443BProduct(readerInterface, uid)

                NfcTagTypes.NFC_TAG_TYPE_A, NfcTagTypes.NFC_TAG_TYPE_B -> tagInfo.productID =
                    ProductID.PRODUCT_UNKNOWN

                else -> tagInfo.productID = ProductID.PRODUCT_UNKNOWN
            }

            // Take advantage that we are in a background thread to allocate the NFCTag.
            try {
                when (tagInfo.productID) {
                    ProductID.PRODUCT_ST_ST25DV64K_I, ProductID.PRODUCT_ST_ST25DV64K_J, ProductID.PRODUCT_ST_ST25DV16K_I, ProductID.PRODUCT_ST_ST25DV16K_J, ProductID.PRODUCT_ST_ST25DV04K_I, ProductID.PRODUCT_ST_ST25DV04K_J -> tagInfo.nfcTag =
                        ST25DVTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25DV04KC_I, ProductID.PRODUCT_ST_ST25DV04KC_J, ProductID.PRODUCT_ST_ST25DV16KC_I, ProductID.PRODUCT_ST_ST25DV16KC_J, ProductID.PRODUCT_ST_ST25DV64KC_I, ProductID.PRODUCT_ST_ST25DV64KC_J -> tagInfo.nfcTag =
                        ST25DVCTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_LRi512 -> tagInfo.nfcTag = LRi512Tag(readerInterface, uid)
                    ProductID.PRODUCT_ST_LRi1K -> tagInfo.nfcTag = LRi1KTag(readerInterface, uid)
                    ProductID.PRODUCT_ST_LRi2K -> tagInfo.nfcTag = LRi2KTag(readerInterface, uid)
                    ProductID.PRODUCT_ST_LRiS2K -> tagInfo.nfcTag = LRiS2KTag(readerInterface, uid)
                    ProductID.PRODUCT_ST_LRiS64K -> tagInfo.nfcTag =
                        LRiS64KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24SR02_Y -> tagInfo.nfcTag =
                        M24SR02KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24SR04_Y, ProductID.PRODUCT_ST_M24SR04_G -> tagInfo.nfcTag =
                        M24SR04KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24SR16_Y -> tagInfo.nfcTag =
                        M24SR16KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24SR64_Y -> tagInfo.nfcTag =
                        M24SR64KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TV512, ProductID.PRODUCT_ST_ST25TV02K -> tagInfo.nfcTag =
                        ST25TVTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TV04K_P -> tagInfo.nfcTag =
                        ST25TV04KPTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TV02KC, ProductID.PRODUCT_ST_ST25TV512C -> tagInfo.nfcTag =
                        ST25TVCTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TV16K -> tagInfo.nfcTag =
                        ST25TV16KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TV64K -> tagInfo.nfcTag =
                        ST25TV64KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25DV02K_W1 -> tagInfo.nfcTag =
                        ST25DV02KW1Tag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25DV02K_W2 -> tagInfo.nfcTag =
                        ST25DV02KW2Tag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24LR16E_R -> tagInfo.nfcTag =
                        M24LR16KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24LR64E_R, ProductID.PRODUCT_ST_M24LR64_R -> tagInfo.nfcTag =
                        M24LR64KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_M24LR04E_R -> tagInfo.nfcTag =
                        M24LR04KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02K -> tagInfo.nfcTag =
                        ST25TA02KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02KB -> tagInfo.nfcTag =
                        ST25TA02KBTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02K_P -> tagInfo.nfcTag =
                        ST25TA02KPTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02KB_P -> tagInfo.nfcTag =
                        ST25TA02KBPTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02K_D -> tagInfo.nfcTag =
                        ST25TA02KDTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA02KB_D -> tagInfo.nfcTag =
                        ST25TA02KBDTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA16K -> tagInfo.nfcTag =
                        ST25TA16KTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA512_K, ProductID.PRODUCT_ST_ST25TA512 -> tagInfo.nfcTag =
                        ST25TA512Tag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA512B -> tagInfo.nfcTag =
                        ST25TA512BTag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TA64K -> tagInfo.nfcTag =
                        ST25TA64KTag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_TYPE4, ProductID.PRODUCT_GENERIC_TYPE4A -> tagInfo.nfcTag =
                        Type4Tag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_TYPE4B -> tagInfo.nfcTag =
                        Type4bTag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_ISO14443B -> tagInfo.nfcTag =
                        Iso14443bTag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_TYPE5_AND_ISO15693 -> tagInfo.nfcTag =
                        STType5Tag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_TYPE5 -> tagInfo.nfcTag =
                        Type5Tag(readerInterface, uid)

                    ProductID.PRODUCT_GENERIC_TYPE2 -> tagInfo.nfcTag =
                        Type2Tag(readerInterface, uid)

                    ProductID.PRODUCT_ST_ST25TN01K, ProductID.PRODUCT_ST_ST25TN512 -> tagInfo.nfcTag =
                        ST25TNTag(readerInterface, uid)

                    else -> {
                        tagInfo.nfcTag = null
                        tagInfo.productID = ProductID.PRODUCT_UNKNOWN
                    }
                }
            } catch (e: STException) {
                // An STException has occured while instantiating the tag
                e.printStackTrace()
                tagInfo.productID = ProductID.PRODUCT_UNKNOWN
            }
            if (tagInfo.nfcTag != null) {
                var manufacturerName = ""
//                try {
//                    manufacturerName = tagInfo.nfcTag.getManufacturerName()
//                } catch (e: STException) {
//                    e.printStackTrace()
//                }
//                if (manufacturerName == "STMicroelectronics") {
//                    tagInfo.nfcTag.setName(tagInfo.productID.toString())
//                }
            }
            return tagInfo
        }
    }
}
