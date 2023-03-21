import Container from "@/app/components/Container";
import moment from "moment";
import { Metadata } from "next";
import Image from "next/image";
import Contents from "./Contents";
import Mutation from "./Mutation";

async function getPost(id: number) {
  const res = await fetch(`http://localhost:4000/posts/${id}`, {
    next: { revalidate: 60 * 60 },
  });
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export async function generateMetadata({
  params,
}: {
  params: {
    postId: number;
  };
}): Promise<Metadata> {
  const post = await getPost(params.postId);
  return { title: post.title };
}

export default async function Page({
  params,
}: {
  params: {
    postId: number;
  };
}) {
  const postId = params.postId;
  const post: Post = await getPost(postId);

  return (
    <>
      <div className='main-img-ratio relative overflow-hidden'>
        <div className='absolute z-10 flex h-full w-full flex-col items-center justify-center gap-10 font-bold'>
          <h1 className='text-3xl text-gray-200'>{post?.title}</h1>
          <div className='flex gap-4 text-gray-200'>
            <div>{post?.author}</div>
            <div>{moment(post?.createdAt).format("YYYY-MM-DD")}</div>
          </div>
        </div>
        <Image
          alt='home-image'
          src={post?.imgUrl || ""}
          className='object-cover'
          fill
        />
        <div className='absolute inset-0 bg-black/40' />
      </div>
      <Container>
        <Contents contents={post?.contents} />
        <Mutation postId={postId} title={post?.title} />
        <div className='h-1 bg-primary' />
        <h3 className='text-center text-3xl'>Comments</h3>
      </Container>
    </>
  );
}

export async function generateStaticParams() {
  const res = await fetch("http://localhost:4000/posts");
  if (!res.ok) {
    throw new Error("Failed to fetch all the posts");
  }
  const posts: Post[] = await res.json();
  return posts.map((post) => ({
    postId: post.id.toString(),
  }));
}
