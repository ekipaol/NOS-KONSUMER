package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParseResponseFile {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fileSize")
    @Expose
    private String fileSize;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("exportStatus")
    @Expose
    private String exportStatus;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("fileVersion")
    @Expose
    private String fileVersion;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publisherId")
    @Expose
    private String publisherId;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("creatorId")
    @Expose
    private String creatorId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("creation")
    @Expose
    private String creation;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("indexed")
    @Expose
    private String indexed;
    @SerializedName("signed")
    @Expose
    private String signed;
    @SerializedName("stamped")
    @Expose
    private String stamped;
    @SerializedName("folderId")
    @Expose
    private String folderId;
    @SerializedName("customId")
    @Expose
    private String customId;
    @SerializedName("immutable")
    @Expose
    private String immutable;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("startPublishing")
    @Expose
    private String startPublishing;
    @SerializedName("pages")
    @Expose
    private String pages;
    @SerializedName("nature")
    @Expose
    private String nature;
    @SerializedName("passwordProtected")
    @Expose
    private String passwordProtected;
    @SerializedName("ocrd")
    @Expose
    private String ocrd;
    @SerializedName("barcoded")
    @Expose
    private String barcoded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIndexed() {
        return indexed;
    }

    public void setIndexed(String indexed) {
        this.indexed = indexed;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getStamped() {
        return stamped;
    }

    public void setStamped(String stamped) {
        this.stamped = stamped;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getImmutable() {
        return immutable;
    }

    public void setImmutable(String immutable) {
        this.immutable = immutable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getStartPublishing() {
        return startPublishing;
    }

    public void setStartPublishing(String startPublishing) {
        this.startPublishing = startPublishing;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPasswordProtected() {
        return passwordProtected;
    }

    public void setPasswordProtected(String passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public String getOcrd() {
        return ocrd;
    }

    public void setOcrd(String ocrd) {
        this.ocrd = ocrd;
    }

    public String getBarcoded() {
        return barcoded;
    }

    public void setBarcoded(String barcoded) {
        this.barcoded = barcoded;
    }
}
