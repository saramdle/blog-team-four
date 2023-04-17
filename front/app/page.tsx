import Container from "./components/Container";
import PostPreview from "./components/PostPreview";
import main from "../public/main.avif";
import MainImage from "./components/MainImage";

export default function Home() {
  return (
    <main>
      <MainImage src={main} title='협찬없는 내돈내산 맛집블로그' />
      <h3 className='py-7 text-center text-3xl font-bold underline underline-offset-8'>
        최신 글
      </h3>
      <Container>
        {/* @ts-expect-error Server Component */}
        <PostPreview />
      </Container>
    </main>
  );
}
