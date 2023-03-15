import Image from "next/image";
import Container from "./components/Container";
import PostPreview from "./components/PostPreview";
import main from "../public/main.avif";

export default function Home() {
  return (
    <main>
      <div className='main-img-ratio relative overflow-hidden'>
        <h1 className='absolute z-10 flex h-full w-full items-center justify-center font-bold text-white sm:text-3xl'>
          협찬없는 내돈내산 맛집블로그
        </h1>
        <Image
          alt='home-image'
          src={main}
          placeholder='blur'
          className='w-full'
        />
        <div className='absolute inset-0 bg-black/40' />
      </div>
      <h3 className='py-7 text-center text-3xl font-bold underline underline-offset-8'>
        인기 글
      </h3>
      <Container>
        <PostPreview />
      </Container>
    </main>
  );
}
