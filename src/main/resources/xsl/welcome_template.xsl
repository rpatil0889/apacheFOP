<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        version="1.0">
    <xsl:output method="xml"/>
    <xsl:template match="userDTO">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-weight="normal" text-align="left">
                        <fo:external-graphic src="url(file:src/main/resources/images/ICICI_Bank_Logo.png)" content-height="8mm" />
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="start" margin ="0.2in"> </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="start">
                    <xsl:text>Hi </xsl:text>
                    <xsl:value-of select="firstName"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="lastName"/>
                    <xsl:text>,</xsl:text>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="start">
                    <xsl:text>Thank you for opening an account with ICICI bank.</xsl:text>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="start">
                    <xsl:text>Your registered email id with us is: </xsl:text>
                    <xsl:value-of select="emailId"/>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="start">
                        <xsl:text>Your mobile number is: </xsl:text>
                        <xsl:value-of select="contactNumber"></xsl:value-of>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>