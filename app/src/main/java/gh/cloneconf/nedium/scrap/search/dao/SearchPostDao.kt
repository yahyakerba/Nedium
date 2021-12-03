package gh.cloneconf.nedium.scrap.search.dao

data class SearchPostDao(
    val b: String,
    val payload: PayloadDao,
    val success: Boolean,
    val v: Int
) {
    data class PayloadDao(
        val paging: PagingDao,
        val references: ReferencesDao,
        val value: List<ValueDao>
    ) {
        data class PagingDao(
            val method: String,
            val next: NextDao?,
            val path: String
        ) {
            data class NextDao(
                val ignoredIds: List<String>,
                val page: Int,
                val pageSize: Int
            )
        }

        data class ReferencesDao(
            val Collection: Map<String, CollectionDao>,
            val User: Map<String, UserDao>
        ) {
                data class CollectionDao(
                    val acceleratedMobilePagesState: Int,
                    val ampLogo: AmpLogoDao,
                    val collectionFeatures: List<Int>,
                    val collectionMastheadId: String,
                    val colorBehavior: Int,
                    val colorPalette: ColorPaletteDao,
                    val creatorId: String,
                    val description: String,
                    val favicon: FaviconDao,
                    val header: HeaderDao,
                    val id: String,
                    val image: ImageDao,
                    val isCurationAllowedByDefault: Boolean,
                    val isOptedIntoAurora: Boolean,
                    val logo: LogoDao,
                    val metadata: MetadataDao,
                    val name: String,
                    val navItems: List<NavItemDao>,
                    val newsletterV3: NewsletterV3Dao,
                    val polarisCoverImage: PolarisCoverImageDao,
                    val ptsQualifiedAt: Long,
                    val shortDescription: String,
                    val slug: String,
                    val subscriberCount: Int,
                    val tagline: String,
                    val tags: List<String>,
                    val type: String,
                    val virtuals: VirtualsDao
                )
                {
                    data class AmpLogoDao(
                        val backgroundSize: String,
                        val filter: String,
                        val height: Int,
                        val imageId: String,
                        val originalHeight: Int,
                        val originalWidth: Int,
                        val strategy: String,
                        val width: Int
                    )

                    data class ColorPaletteDao(
                        val darkBackgroundSpectrum: DarkBackgroundSpectrumDao,
                        val defaultBackgroundSpectrum: DefaultBackgroundSpectrumDao,
                        val highlightSpectrum: HighlightSpectrumDao
                    ) {
                        data class DarkBackgroundSpectrumDao(
                            val backgroundColor: String,
                            val colorPoints: List<ColorPointDao>
                        )

                        data class DefaultBackgroundSpectrumDao(
                            val backgroundColor: String,
                            val colorPoints: List<ColorPointDao>
                        )

                        data class HighlightSpectrumDao(
                            val backgroundColor: String,
                            val colorPoints: List<ColorPointDao>
                        )
                    }

                    data class FaviconDao(
                        val backgroundSize: String,
                        val filter: String,
                        val height: Int,
                        val imageId: String,
                        val originalHeight: Int,
                        val originalWidth: Int,
                        val strategy: String,
                        val width: Int
                    )

                    data class HeaderDao(
                        val alignment: Int,
                        val backgroundImage: BackgroundImageDao,
                        val description: String,
                        val layout: Int,
                        val logoImage: LogoImageDao,
                        val title: String
                    ) {
                        class BackgroundImageDao

                        class LogoImageDao
                    }

                    data class ImageDao(
                        val backgroundSize: String,
                        val filter: String,
                        val height: Int,
                        val imageId: String,
                        val originalHeight: Int,
                        val originalWidth: Int,
                        val strategy: String,
                        val width: Int
                    )

                    data class LogoDao(
                        val backgroundSize: String,
                        val filter: String,
                        val height: Int,
                        val imageId: String,
                        val originalHeight: Int,
                        val originalWidth: Int,
                        val strategy: String,
                        val width: Int
                    )

                    data class MetadataDao(
                        val activeAt: Long,
                        val followerCount: Int
                    )

                    data class NavItemDao(
                        val postId: String,
                        val source: String,
                        val title: String,
                        val type: Int,
                        val url: String
                    )

                    data class NewsletterV3Dao(
                        val avatarImageId: String,
                        val collectionId: String,
                        val creatorId: String,
                        val description: String,
                        val exportableSubscribersCount: Int,
                        val isSubscribed: Boolean,
                        val name: String,
                        val newsletterSlug: String,
                        val newsletterV3Id: String,
                        val promoBody: String,
                        val promoHeadline: String,
                        val replyToEmail: String,
                        val showNewsletterPostsInCollectionHome: Boolean,
                        val showPromo: Boolean,
                        val subscribersCount: Int,
                        val type: Int
                    )

                    data class PolarisCoverImageDao(
                        val backgroundSize: String,
                        val filter: String,
                        val height: Int,
                        val imageId: String,
                        val originalHeight: Int,
                        val originalWidth: Int,
                        val strategy: String,
                        val width: Int
                    )

                    data class VirtualsDao(
                        val canToggleEmail: Boolean,
                        val isEligibleForHightower: Boolean,
                        val isEnrolledInHightower: Boolean,
                        val isMuted: Boolean,
                        val isSubscribed: Boolean,
                        val isSubscribedToCollectionEmails: Boolean,
                        val isWriter: Boolean,
                        val permissions: PermissionsDao
                    ) {
                        data class PermissionsDao(
                            val canAddWriters: Boolean,
                            val canBeAssignedAuthor: Boolean,
                            val canCreateNewsletterV3: Boolean,
                            val canEditOwnPosts: Boolean,
                            val canEditPosts: Boolean,
                            val canEnrollInHightower: Boolean,
                            val canLockOwnPostsForMediumMembers: Boolean,
                            val canLockPostsForMediumMembers: Boolean,
                            val canManageAll: Boolean,
                            val canPublish: Boolean,
                            val canPublishAll: Boolean,
                            val canRemove: Boolean,
                            val canRepublish: Boolean,
                            val canSendNewsletter: Boolean,
                            val canSubmit: Boolean,
                            val canViewCloaked: Boolean,
                            val canViewLockedPosts: Boolean,
                            val canViewNewsletterV2Stats: Boolean,
                            val canViewStats: Boolean
                        )
                    }
                }
        }

            data class UserDao(
                val allowNotes: Int,
                val backgroundImageId: String,
                val bio: String,
                val createdAt: Long,
                val hasCompletedProfile: Boolean,
                val hasSeenIcelandOnboarding: Boolean,
                val imageId: String,
                val isCreatorPartnerProgramEnrolled: Boolean,
                val isMembershipTrialEligible: Boolean,
                val isSuspended: Boolean,
                val isWriterProgramEnrolled: Boolean,
                val mediumMemberAt: Long,
                val name: String,
                val optInToIceland: Boolean,
                val postSubscribeMembershipUpsellShownAt: Int,
                val replyToEmailBannerShownCount: Int,
                val twitterScreenName: String,
                val type: String,
                val userDismissableFlags: List<Int>,
                val userId: String,
                val username: String
            )

        }

        data class ValueDao(
            val acceptedAt: Long,
            val allowResponses: Boolean,
            val approvedHomeCollectionId: String,
            val audioVersionDurationSec: Long,
            val audioVersionUrl: String,
            val canonicalUrl: String,
            val cardType: Int,
            val coverless: Boolean,
            val createdAt: Long,
            val creatorId: String,
            val curationEligibleAt: Long,
            val deletedAt: Int,
            val detectedLanguage: String,
            val displayAuthor: String,
            val editorialPreviewDek: String,
            val editorialPreviewTitle: String,
            val experimentalCss: String,
            val featureLockRequestAcceptedAt: Long,
            val firstPublishedAt: Long,
            val hasUnpublishedEdits: Boolean,
            val hightowerMinimumGuaranteeEndsAt: Long,
            val hightowerMinimumGuaranteeStartsAt: Long,
            val homeCollectionId: String,
            val id: String,
            val importedPublishedAt: Long,
            val importedUrl: String,
            val inResponseToMediaResourceId: String,
            val inResponseToPostId: String,
            val inResponseToRemovedAt: Int,
            val isApprovedTranslation: Boolean,
            val isBlockedFromHightower: Boolean,
            val isDistributionAlertDismissed: Boolean,
            val isEligibleForRevenue: Boolean,
            val isLimitedState: Boolean,
            val isLockedResponse: Boolean,
            val isMarkedPaywallOnly: Boolean,
            val isNewsletter: Boolean,
            val isProxyPost: Boolean,
            val isPublishToEmail: Boolean,
            val isSeries: Boolean,
            val isShortform: Boolean,
            val isSubscriptionLocked: Boolean,
            val isSuspended: Boolean,
            val isTitleSynthesized: Boolean,
            val latestPublishedAt: Long,
            val latestPublishedVersion: String,
            val latestRev: Int,
            val latestVersion: String,
            val layerCake: Int,
            val license: Int,
            val lockedPostSource: Int,
            val mediumUrl: String,
            val migrationId: String,
            val mongerRequestType: Int,
            val newsletterId: String,
            val notifyFacebook: Boolean,
            val notifyFollowers: Boolean,
            val notifyTwitter: Boolean,
            val previewContent: PreviewContentDao,
            val previewContent2: PreviewContent2Dao,
            val primaryTopicId: String,
            val proxyPostFaviconUrl: String,
            val proxyPostProviderName: String,
            val proxyPostType: Int,
            val responseDistribution: Int,
            val responseHiddenOnParentPostAt: Int,
            val responsesLocked: Boolean,
            val seoTitle: String,
            val sequenceId: String,
            val seriesLastAppendedAt: Long,
            val shortformType: Int,
            val slug: String,
            val socialDek: String,
            val socialTitle: String,
            val title: String,
            val translationSourceCreatorId: String,
            val translationSourcePostId: String,
            val type: String,
            val uniqueSlug: String,
            val updatedAt: Long,
            val versionId: String,
            val virtuals: VirtualsDao,
            val visibility: Int,
            val vote: Boolean,
            val webCanonicalUrl: String
        ) {
            data class PreviewContentDao(
                val bodyModel: BodyModelDao,
                val isFullContent: Boolean,
                val subtitle: String
            ) {
                data class BodyModelDao(
                    val paragraphs: List<ParagraphDao>,
                    val sections: List<SectionDao>
                ) {
                    data class ParagraphDao(
                        val alignment: Int,
                        val layout: Int,
                        val markups: List<MarkupDao>,
                        val metadata: MetadataDao,
                        val name: String,
                        val text: String,
                        val type: Int
                    ) {
                        data class MarkupDao(
                            val anchorType: Int,
                            val end: Int,
                            val href: String,
                            val rel: String,
                            val start: Int,
                            val title: String,
                            val type: Int
                        )

                        data class MetadataDao(
                            val focusPercentX: Int,
                            val focusPercentY: Int,
                            val id: String,
                            val isFeatured: Boolean,
                            val originalHeight: Int,
                            val originalWidth: Int
                        )
                    }

                    data class SectionDao(
                        val startIndex: Int
                    )
                }
            }

            data class PreviewContent2Dao(
                val bodyModel: BodyModelDao,
                val isFullContent: Boolean,
                val subtitle: String
            ) {
                data class BodyModelDao(
                    val paragraphs: List<ParagraphDao>,
                    val sections: List<SectionDao>
                ) {
                    data class ParagraphDao(
                        val dropCapImage: DropCapImageDao,
                        val hasDropCap: Boolean,
                        val layout: Int,
                        val markups: List<MarkupDao>,
                        val metadata: MetadataDao,
                        val mixtapeMetadata: MixtapeMetadataDao,
                        val name: String,
                        val text: String,
                        val type: Int
                    ) {
                        data class DropCapImageDao(
                            val id: String,
                            val originalHeight: Int,
                            val originalWidth: Int
                        )

                        data class MarkupDao(
                            val anchorType: Int,
                            val end: Int,
                            val href: String,
                            val rel: String,
                            val start: Int,
                            val title: String,
                            val type: Int
                        )

                        data class MetadataDao(
                            val id: String,
                            val isFeatured: Boolean,
                            val originalHeight: Int,
                            val originalWidth: Int
                        )

                        data class MixtapeMetadataDao(
                            val href: String,
                            val mediaResourceId: String,
                            val thumbnailImageId: String
                        )
                    }

                    data class SectionDao(
                        val name: String,
                        val startIndex: Int
                    )
                }
            }

            data class VirtualsDao(
                val allowNotes: Boolean,
                val imageCount: Int,
                val isBookmarked: Boolean,
                val isLockedPreviewOnly: Boolean,
                val links: LinksDao,
                val metaDescription: String,
                val noIndex: Boolean,
                val previewImage: PreviewImageDao,
                val publishedInCount: Int,
                val readingList: Int,
                val readingTime: Double,
                val recommends: Int,
                val responsesCreatedCount: Int,
                val sectionCount: Int,
                val socialRecommendsCount: Int,
                val statusForCollection: String,
                val subtitle: String,
                val tags: List<TagDao>,
                val topics: List<TopicDao>,
                val totalClapCount: Int,
                val usersBySocialRecommends: List<Any>,
                val wordCount: Int
            ) {
                data class LinksDao(
                    val entries: List<EntryDao>,
                    val generatedAt: Long,
                    val version: String
                ) {
                    data class EntryDao(
                        val alts: List<AltDao>,
                        val httpStatus: Int,
                        val url: String
                    ) {
                        data class AltDao(
                            val type: Int,
                            val url: String
                        )
                    }
                }

                data class PreviewImageDao(
                    val backgroundSize: String,
                    val filter: String,
                    val focusPercentX: Int,
                    val focusPercentY: Int,
                    val height: Int,
                    val imageId: String,
                    val originalHeight: Int,
                    val originalWidth: Int,
                    val strategy: String,
                    val width: Int
                )

                data class TagDao(
                    val metadata: MetadataDao,
                    val name: String,
                    val postCount: Int,
                    val slug: String,
                    val type: String
                ) {
                    data class MetadataDao(
                        val coverImage: CoverImageDao,
                        val postCount: Int
                    ) {
                        data class CoverImageDao(
                            val alt: String,
                            val id: String,
                            val isFeatured: Boolean,
                            val originalHeight: Int,
                            val originalWidth: Int,
                            val unsplashPhotoId: String
                        )
                    }
                }

                data class TopicDao(
                    val createdAt: Long,
                    val deletedAt: Int,
                    val description: String,
                    val image: ImageDao,
                    val name: String,
                    val relatedTags: List<Any>,
                    val relatedTopicIds: List<Any>,
                    val relatedTopics: List<Any>,
                    val seoTitle: String,
                    val slug: String,
                    val topicId: String,
                    val type: String,
                    val visibility: Int
                ) {
                    data class ImageDao(
                        val id: String,
                        val originalHeight: Int,
                        val originalWidth: Int
                    )
                }
            }
        }

        data class ColorPointDao(
            val color: String,
            val point: Any
        )
    }
