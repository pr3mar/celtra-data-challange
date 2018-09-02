USE [celtra_challenge]
GO
/****** Object:  Table [dbo].[Ad]    Script Date: 2018-09-02 17:54:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ad](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[AdName] [nvarchar](50) NOT NULL,
	[CampaignId] [bigint] NOT NULL,
	[DateStored] [datetime] NOT NULL,
 CONSTRAINT [PK_Ad] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Campaign]    Script Date: 2018-09-02 17:54:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Campaign](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[CampaignName] [nvarchar](250) NOT NULL,
	[DateStarted] [datetime] NULL,
	[DateEnded] [datetime] NULL,
	[DateStored] [datetime] NOT NULL,
 CONSTRAINT [PK_Campaign] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Impression]    Script Date: 2018-09-02 17:54:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Impression](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[CampaignId] [bigint] NOT NULL,
	[AdId] [bigint] NOT NULL,
	[UserId] [bigint] NOT NULL,
	[InteractionTypeId] [int] NULL,
	[DateOccurred] [datetime] NOT NULL,
	[DateStored] [datetime] NOT NULL,
 CONSTRAINT [PK_Impression] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InteractionType]    Script Date: 2018-09-02 17:54:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InteractionType](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Type] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_InteractionType] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 2018-09-02 17:54:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](150) NOT NULL,
	[DateStored] [datetime] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Ad] ADD  CONSTRAINT [DF_Ad_DateStored]  DEFAULT (getutcdate()) FOR [DateStored]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF_Campaign_DateStored]  DEFAULT (getutcdate()) FOR [DateStored]
GO
ALTER TABLE [dbo].[Impression] ADD  CONSTRAINT [DF_Impression_DateStored]  DEFAULT (getutcdate()) FOR [DateStored]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_DateStored]  DEFAULT (getutcdate()) FOR [DateStored]
GO
ALTER TABLE [dbo].[Ad]  WITH CHECK ADD  CONSTRAINT [FK_Ad_Campaign] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([ID])
GO
ALTER TABLE [dbo].[Ad] CHECK CONSTRAINT [FK_Ad_Campaign]
GO
ALTER TABLE [dbo].[Impression]  WITH CHECK ADD  CONSTRAINT [FK_Impression_Ad] FOREIGN KEY([AdId])
REFERENCES [dbo].[Ad] ([ID])
GO
ALTER TABLE [dbo].[Impression] CHECK CONSTRAINT [FK_Impression_Ad]
GO
ALTER TABLE [dbo].[Impression]  WITH CHECK ADD  CONSTRAINT [FK_Impression_Campaign] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([ID])
GO
ALTER TABLE [dbo].[Impression] CHECK CONSTRAINT [FK_Impression_Campaign]
GO
ALTER TABLE [dbo].[Impression]  WITH CHECK ADD  CONSTRAINT [FK_Impression_InteractionType] FOREIGN KEY([InteractionTypeId])
REFERENCES [dbo].[InteractionType] ([ID])
GO
ALTER TABLE [dbo].[Impression] CHECK CONSTRAINT [FK_Impression_InteractionType]
GO
ALTER TABLE [dbo].[Impression]  WITH CHECK ADD  CONSTRAINT [FK_Impression_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([ID])
GO
ALTER TABLE [dbo].[Impression] CHECK CONSTRAINT [FK_Impression_User]
GO
