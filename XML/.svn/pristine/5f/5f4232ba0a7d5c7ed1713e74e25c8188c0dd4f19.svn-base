<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
		
			<head>
			
			<title>Zoo</title>
			<link href="zoo.css" rel="stylesheet" type="text/css"/>
			</head>
		
			<body>
				<h1>Bienvenue au Zoo de La Miage</h1>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>


	<xsl:template match="info|attention|danger">
		<xsl:choose>
			<xsl:when test="parent::dauphins or parent::sélaciens">
				<tr>
					<td colspan="6"><xsl:call-template name="avertissement"/></td>
				</tr>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="avertissement"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="avertissement">
		<table class="{name()}" align="center">
			<tr>
				<td><img src="{name()}.gif"/></td>
				<td><xsl:apply-templates/></td>
			</tr>
		</table>
	</xsl:template>

 	<xsl:template match="b"><b><xsl:apply-templates /></b></xsl:template>
	<xsl:template match="i"><i><xsl:apply-templates /></i></xsl:template>
	<xsl:template match="em"><em><xsl:apply-templates /></em></xsl:template>
	<xsl:template match="strong"><strong><xsl:apply-templates /></strong></xsl:template>
	
	<xsl:template match="dauphin|requin">
			<tr>
				<td><xsl:value-of select="nom" /></td>
				<td><xsl:value-of select="@date-naissance" /></td>
				<td><xsl:value-of select="@espèce" /><br /><i><xsl:value-of select="@nom-savant" /></i></td>
				<td><xsl:value-of select="taille" />&#160;<xsl:value-of select="taille/@unité" /></td>
				<td><xsl:value-of select="poids" />&#160;<xsl:value-of select="poids/@unité" /></td>
				<td><xsl:value-of select="sexe" /></td>
				<xsl:if test="@photo">
					<td><img src="{@photo}" alt="{nom}" /></td>
				</xsl:if>
			</tr>
	</xsl:template>
	
</xsl:stylesheet>