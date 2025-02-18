import { css, styled } from 'styled-components';
import TagInput from '../@common/TagInput/TagInput';
import Button from '../@common/Button/Button';
import Spinner from 'components/@common/Spinner/Spinner';
import { LeftArrowHeadIcon, TagIcon } from 'assets/icons';
import { slide } from 'styles/animation';
import { TabKeys } from 'components/WritingSideBar/WritingSideBar';
import { usePublishingPropertySection } from './usePublishingPropertySection';
import type { Blog } from 'types/domain';

type Props = {
  writingId: number;
  publishTo: Blog;
  selectCurrentTab: (tabKey: TabKeys) => void;
};

const PublishingPropertySection = ({ writingId, publishTo, selectCurrentTab }: Props) => {
  const { isLoading, setTags, publishWritingToBlog } = usePublishingPropertySection({
    selectCurrentTab,
  });

  if (isLoading)
    return (
      <S.LoadingWrapper>
        글을 발행하고 있어요
        <Spinner />
      </S.LoadingWrapper>
    );

  return (
    <S.PublishingPropertySection $blog={publishTo}>
      <S.SectionHeader>
        <button
          onClick={() => selectCurrentTab(TabKeys.Publishing)}
          aria-label='발행 블로그 플랫폼 선택란으로 이동'
        >
          <LeftArrowHeadIcon width={14} height={14} />
        </button>
        발행 정보
      </S.SectionHeader>
      <S.Properties>
        <S.PropertyRow>
          <S.PropertyName>
            <TagIcon width={12} height={12} />
            Tags
          </S.PropertyName>
          <div>
            <TagInput onChangeTags={setTags} />
          </div>
        </S.PropertyRow>
      </S.Properties>
      <Button
        block
        variant='secondary'
        onClick={() => publishWritingToBlog({ writingId, publishTo })}
      >
        발행하기
      </Button>
    </S.PublishingPropertySection>
  );
};

export default PublishingPropertySection;

const S = {
  PublishingPropertySection: styled.section<{ $blog: Blog }>`
    display: flex;
    flex-direction: column;
    gap: 2rem;
    animation: ${slide} 0.5s;

    ${({ theme, $blog }) => css`
      & > button {
        outline-color: ${theme.color[$blog.toLowerCase()]};
        background-color: ${theme.color[$blog.toLowerCase()]};

        &:hover {
          background-color: ${theme.color[$blog.toLowerCase()]};
        }
      }
    `};
  `,
  SectionHeader: styled.h1`
    display: flex;
    gap: 1.5rem;
    font-size: 1.5rem;
    font-weight: 700;
    line-height: 1.5rem;
  `,
  Properties: styled.div`
    padding: 0 0 1rem 0.9rem;
  `,
  PropertyRow: styled.div`
    display: flex;
    align-items: flex-start;
  `,
  PropertyName: styled.div`
    display: flex;
    align-items: center;
    flex-shrink: 0;
    width: 9.5rem;
    height: 2.3rem;
    color: ${({ theme }) => theme.color.gray8};
    font-size: 1.3rem;
    font-weight: 600;
  `,
  LoadingWrapper: styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 4rem;
    font-size: 1.3rem;
  `,
};
