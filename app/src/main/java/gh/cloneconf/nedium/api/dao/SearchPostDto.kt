package gh.cloneconf.nedium.api.dao

import androidx.annotation.Keep

@Keep
data class SearchPostDto(
//    val b: String,
    val payload: PayloadDto,
//    val success: Boolean,
//    val v: Int
) {
    data class PayloadDto(
        val paging: PagingDto,
//        val references: ReferencesDto,
        val value: List<ValueDto>
    ) {
        data class PagingDto(
//            val method: String,
            val next: NextDto?,
//            val path: String
        ) {
            data class NextDto(
                val ignoredIds: List<String>,
                val page: Int,
                val pageSize: Int
            )
        }

//        data class ReferencesDto(
//            val Collection: CollectionDto,
//            val User: UserDto
//        ) {
//                data class CollectionDto(
//                    val acceleratedMobilePagesState: Int,
//                    val ampLogo: AmpLogoDto,
//                    val collectionFeatures: List<Int>,
//                    val collectionMastheadId: String,
//                    val colorBehavior: Int,
//                    val colorPalette: ColorPaletteDto,
//                    val creatorId: String,
//                    val description: String,
//                    val favicon: FaviconDto,
//                    val header: HeaderDto,
//                    val id: String,
//                    val image: ImageDto,
//                    val isCurationAllowedByDefault: Boolean,
//                    val isOptedIntoAurora: Boolean,
//                    val logo: LogoDto,
//                    val metadata: MetadataDto,
//                    val name: String,
//                    val navItems: List<NavItemDto>,
//                    val newsletterV3: NewsletterV3Dto,
//                    val polarisCoverImage: PolarisCoverImageDto,
//                    val ptsQualifiedAt: Long,
//                    val shortDescription: String,
//                    val slug: String,
//                    val subscriberCount: Int,
//                    val tagline: String,
//                    val tags: List<String>,
//                    val type: String,
//                    val virtuals: VirtualsDto
//                )
//                {
//                    data class AmpLogoDto(
//                        val backgroundSize: String,
//                        val filter: String,
//                        val height: Int,
//                        val imageId: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int,
//                        val strategy: String,
//                        val width: Int
//                    )
//
//                    data class ColorPaletteDto(
//                        val darkBackgroundSpectrum: DarkBackgroundSpectrumDto,
//                        val defaultBackgroundSpectrum: DefaultBackgroundSpectrumDto,
//                        val highlightSpectrum: HighlightSpectrumDto
//                    ) {
//                        data class DarkBackgroundSpectrumDto(
//                            val backgroundColor: String,
//                            val colorPoints: List<ColorPointDto>
//                        ) {
//                            data class ColorPointDto(
//                                val color: String,
//                                val point: Int
//                            )
//                        }
//
//                        data class DefaultBackgroundSpectrumDto(
//                            val backgroundColor: String,
//                            val colorPoints: List<ColorPointDto>
//                        ) {
//                            data class ColorPointDto(
//                                val color: String,
//                                val point: Int
//                            )
//                        }
//
//                        data class HighlightSpectrumDto(
//                            val backgroundColor: String,
//                            val colorPoints: List<ColorPointDto>
//                        ) {
//                            data class ColorPointDto(
//                                val color: String,
//                                val point: Int
//                            )
//                        }
//                    }
//
//                    data class FaviconDto(
//                        val backgroundSize: String,
//                        val filter: String,
//                        val height: Int,
//                        val imageId: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int,
//                        val strategy: String,
//                        val width: Int
//                    )
//
//                    data class HeaderDto(
//                        val alignment: Int,
//                        val backgroundImage: BackgroundImageDto,
//                        val description: String,
//                        val layout: Int,
//                        val logoImage: LogoImageDto,
//                        val title: String
//                    ) {
//                        class BackgroundImageDto
//
//                        class LogoImageDto
//                    }
//
//                    data class ImageDto(
//                        val backgroundSize: String,
//                        val filter: String,
//                        val height: Int,
//                        val imageId: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int,
//                        val strategy: String,
//                        val width: Int
//                    )
//
//                    data class LogoDto(
//                        val backgroundSize: String,
//                        val filter: String,
//                        val height: Int,
//                        val imageId: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int,
//                        val strategy: String,
//                        val width: Int
//                    )
//
//                    data class MetadataDto(
//                        val activeAt: Long,
//                        val followerCount: Int
//                    )
//
//                    data class NavItemDto(
//                        val postId: String,
//                        val source: String,
//                        val title: String,
//                        val type: Int,
//                        val url: String
//                    )
//
//                    data class NewsletterV3Dto(
//                        val avatarImageId: String,
//                        val collectionId: String,
//                        val creatorId: String,
//                        val description: String,
//                        val exportableSubscribersCount: Int,
//                        val isSubscribed: Boolean,
//                        val name: String,
//                        val newsletterSlug: String,
//                        val newsletterV3Id: String,
//                        val promoBody: String,
//                        val promoHeadline: String,
//                        val replyToEmail: String,
//                        val showNewsletterPostsInCollectionHome: Boolean,
//                        val showPromo: Boolean,
//                        val subscribersCount: Int,
//                        val type: Int
//                    )
//
//                    data class PolarisCoverImageDto(
//                        val backgroundSize: String,
//                        val filter: String,
//                        val height: Int,
//                        val imageId: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int,
//                        val strategy: String,
//                        val width: Int
//                    )
//
//                    data class VirtualsDto(
//                        val canToggleEmail: Boolean,
//                        val isEligibleForHightower: Boolean,
//                        val isEnrolledInHightower: Boolean,
//                        val isMuted: Boolean,
//                        val isSubscribed: Boolean,
//                        val isSubscribedToCollectionEmails: Boolean,
//                        val isWriter: Boolean,
//                        val permissions: PermissionsDto
//                    ) {
//                        data class PermissionsDto(
//                            val canAddWriters: Boolean,
//                            val canBeAssignedAuthor: Boolean,
//                            val canCreateNewsletterV3: Boolean,
//                            val canEditOwnPosts: Boolean,
//                            val canEditPosts: Boolean,
//                            val canEnrollInHightower: Boolean,
//                            val canLockOwnPostsForMediumMembers: Boolean,
//                            val canLockPostsForMediumMembers: Boolean,
//                            val canManageAll: Boolean,
//                            val canPublish: Boolean,
//                            val canPublishAll: Boolean,
//                            val canRemove: Boolean,
//                            val canRepublish: Boolean,
//                            val canSendNewsletter: Boolean,
//                            val canSubmit: Boolean,
//                            val canViewCloaked: Boolean,
//                            val canViewLockedPosts: Boolean,
//                            val canViewNewsletterV2Stats: Boolean,
//                            val canViewStats: Boolean
//                        )
//                    }
//                }
//        }
//            data class UserDto(
//                val allowNotes: Int,
//                val backgroundImageId: String,
//                val bio: String,
//                val createdAt: Long,
//                val hasCompletedProfile: Boolean,
//                val hasSeenIcelandOnboarding: Boolean,
//                val imageId: String,
//                val isCreatorPartnerProgramEnrolled: Boolean,
//                val isMembershipTrialEligible: Boolean,
//                val isSuspended: Boolean,
//                val isWriterProgramEnrolled: Boolean,
//                val mediumMemberAt: Int,
//                val name: String,
//                val optInToIceland: Boolean,
//                val postSubscribeMembershipUpsellShownAt: Int,
//                val replyToEmailBannerShownCount: Int,
//                val twitterScreenName: String,
//                val type: String,
//                val userDismissableFlags: List<Int>,
//                val userId: String,
//                val username: String
//            )
        }


        data class ValueDto(
//            val acceptedAt: Long,
//            val allowResponses: Boolean,
//            val approvedHomeCollectionId: String,
//            val audioVersionDurationSec: Int,
//            val audioVersionUrl: String,
//            val canonicalUrl: String,
//            val cardType: Int,
//            val coverless: Boolean,
//            val createdAt: Long,
//            val creatorId: String,
//            val curationEligibleAt: Long,
//            val deletedAt: Int,
//            val detectedLanguage: String,
//            val displayAuthor: String,
//            val editorialPreviewDek: String,
//            val editorialPreviewTitle: String,
//            val experimentalCss: String,
//            val featureLockRequestAcceptedAt: Long,
//            val firstPublishedAt: Long,
//            val hasUnpublishedEdits: Boolean,
//            val hightowerMinimumGuaranteeEndsAt: Long,
//            val hightowerMinimumGuaranteeStartsAt: Long,
//            val homeCollectionId: String,
            val id: String,
//            val importedPublishedAt: Long,
//            val importedUrl: String,
//            val inResponseToMediaResourceId: String,
//            val inResponseToPostId: String,
//            val inResponseToRemovedAt: Int,
//            val isApprovedTranslation: Boolean,
//            val isBlockedFromHightower: Boolean,
//            val isDistributionAlertDismissed: Boolean,
//            val isEligibleForRevenue: Boolean,
//            val isLimitedState: Boolean,
//            val isLockedResponse: Boolean,
//            val isMarkedPaywallOnly: Boolean,
//            val isNewsletter: Boolean,
//            val isProxyPost: Boolean,
//            val isPublishToEmail: Boolean,
//            val isSeries: Boolean,
//            val isShortform: Boolean,
//            val isSubscriptionLocked: Boolean,
//            val isSuspended: Boolean,
//            val isTitleSynthesized: Boolean,
//            val latestPublishedAt: Long,
//            val latestPublishedVersion: String,
//            val latestRev: Int,
//            val latestVersion: String,
//            val layerCake: Int,
//            val license: Int,
//            val lockedPostSource: Int,
//            val mediumUrl: String,
//            val migrationId: String,
//            val mongerRequestType: Int,
//            val newsletterId: String,
//            val notifyFacebook: Boolean,
//            val notifyFollowers: Boolean,
//            val notifyTwitter: Boolean,
            val previewContent: PreviewContentDto,
//            val previewContent2: PreviewContent2Dto,
//            val primaryTopicId: String,
//            val proxyPostFaviconUrl: String,
//            val proxyPostProviderName: String,
//            val proxyPostType: Int,
//            val responseDistribution: Int,
//            val responseHiddenOnParentPostAt: Int,
//            val responsesLocked: Boolean,
//            val seoTitle: String,
//            val sequenceId: String,
//            val seriesLastAppendedAt: Int,
//            val shortformType: Int,
//            val slug: String,
//            val socialDek: String,
//            val socialTitle: String,
            val title: String,
//            val translationSourceCreatorId: String,
//            val translationSourcePostId: String,
//            val type: String,
//            val uniqueSlug: String,
//            val updatedAt: Long,
//            val versionId: String,
//            val virtuals: VirtualsDto,
//            val visibility: Int,
//            val vote: Boolean,
//            val webCanonicalUrl: String
        ) {
            data class PreviewContentDto(
                val bodyModel: BodyModelDto,
                val isFullContent: Boolean,
                val subtitle: String
            ) {
                data class BodyModelDto(
                    val paragraphs: List<ParagraphDto>,
                    val sections: List<SectionDto>
                ) {
                    data class ParagraphDto(
                        val alignment: Int,
                        val layout: Int,
                        val markups: List<MarkupDto>,
                        val metadata: MetadataDto,
                        val name: String,
                        val text: String,
                        val type: Int
                    ) {
                        data class MarkupDto(
                            val anchorType: Int,
                            val end: Int,
                            val href: String,
                            val rel: String,
                            val start: Int,
                            val title: String,
                            val type: Int
                        )

                        data class MetadataDto(
                            val focusPercentX: Int,
                            val focusPercentY: Int,
                            val id: String,
                            val isFeatured: Boolean,
                            val originalHeight: Int,
                            val originalWidth: Int
                        )
                    }

                    data class SectionDto(
                        val startIndex: Int
                    )
                }
            }

//            data class PreviewContent2Dto(
//                val bodyModel: BodyModelDto,
//                val isFullContent: Boolean,
//                val subtitle: String
//            ) {
//                data class BodyModelDto(
//                    val paragraphs: List<ParagraphDto>,
//                    val sections: List<SectionDto>
//                ) {
//                    data class ParagraphDto(
//                        val dropCapImage: DropCapImageDto,
//                        val hasDropCap: Boolean,
//                        val layout: Int,
//                        val markups: List<MarkupDto>,
//                        val metadata: MetadataDto,
//                        val mixtapeMetadata: MixtapeMetadataDto,
//                        val name: String,
//                        val text: String,
//                        val type: Int
//                    ) {
//                        data class DropCapImageDto(
//                            val id: String,
//                            val originalHeight: Int,
//                            val originalWidth: Int
//                        )
//
//                        data class MarkupDto(
//                            val anchorType: Int,
//                            val end: Int,
//                            val href: String,
//                            val rel: String,
//                            val start: Int,
//                            val title: String,
//                            val type: Int
//                        )
//
//                        data class MetadataDto(
//                            val id: String,
//                            val isFeatured: Boolean,
//                            val originalHeight: Int,
//                            val originalWidth: Int
//                        )
//
//                        data class MixtapeMetadataDto(
//                            val href: String,
//                            val mediaResourceId: String,
//                            val thumbnailImageId: String
//                        )
//                    }
//
//                    data class SectionDto(
//                        val name: String,
//                        val startIndex: Int
//                    )
//                }
//            }
//
//            data class VirtualsDto(
//                val allowNotes: Boolean,
//                val imageCount: Int,
//                val isBookmarked: Boolean,
//                val isLockedPreviewOnly: Boolean,
//                val links: LinksDto,
//                val metaDescription: String,
//                val noIndex: Boolean,
//                val previewImage: PreviewImageDto,
//                val publishedInCount: Int,
//                val readingList: Int,
//                val readingTime: Double,
//                val recommends: Int,
//                val responsesCreatedCount: Int,
//                val sectionCount: Int,
//                val socialRecommendsCount: Int,
//                val statusForCollection: String,
//                val subtitle: String,
//                val tags: List<TagDto>,
//                val topics: List<TopicDto>,
//                val totalClapCount: Int,
//                val usersBySocialRecommends: List<Any>,
//                val wordCount: Int
//            ) {
//                data class LinksDto(
//                    val entries: List<EntryDto>,
//                    val generatedAt: Long,
//                    val version: String
//                ) {
//                    data class EntryDto(
//                        val alts: List<AltDto>,
//                        val httpStatus: Int,
//                        val url: String
//                    ) {
//                        data class AltDto(
//                            val type: Int,
//                            val url: String
//                        )
//                    }
//                }

//                data class PreviewImageDto(
//                    val backgroundSize: String,
//                    val filter: String,
//                    val focusPercentX: Int,
//                    val focusPercentY: Int,
//                    val height: Int,
//                    val imageId: String,
//                    val originalHeight: Int,
//                    val originalWidth: Int,
//                    val strategy: String,
//                    val width: Int
//                )
//
//                data class TagDto(
//                    val metadata: MetadataDto,
//                    val name: String,
//                    val postCount: Int,
//                    val slug: String,
//                    val type: String
//                ) {
//                    data class MetadataDto(
//                        val coverImage: CoverImageDto,
//                        val postCount: Int
//                    ) {
//                        data class CoverImageDto(
//                            val alt: String,
//                            val id: String,
//                            val isFeatured: Boolean,
//                            val originalHeight: Int,
//                            val originalWidth: Int,
//                            val unsplashPhotoId: String
//                        )
//                    }
//                }

//                data class TopicDto(
//                    val createdAt: Long,
//                    val deletedAt: Int,
//                    val description: String,
//                    val image: ImageDto,
//                    val name: String,
//                    val relatedTags: List<Any>,
//                    val relatedTopicIds: List<Any>,
//                    val relatedTopics: List<Any>,
//                    val seoTitle: String,
//                    val slug: String,
//                    val topicId: String,
//                    val type: String,
//                    val visibility: Int
//                ) {
//                    data class ImageDto(
//                        val id: String,
//                        val originalHeight: Int,
//                        val originalWidth: Int
//                    )
//                }
//            }
        }
    }
